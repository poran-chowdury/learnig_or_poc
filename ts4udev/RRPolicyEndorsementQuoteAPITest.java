package automation.policyquote;

import org.json.JSONArray;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.clientapi.common.Configuration;
import utils.clientapi.common.ConfigurationHelper;
import utils.clientapi.common.security.AuthProvider;
import utils.testutils.cap.PolicyQuoteTestUtil;
import utils.testutils.common.AuthHelper;
import utils.testutils.common.ItestTestUtil;
import utils.testutils.common.PivotTestFixture;
import utils.testutils.geo.GeoTestUtil;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static automation.policyquote.RRJsonKeys.*;

public class RRPolicyEndorsementQuoteAPITest {
   private static final Logger logger = LoggerFactory.getLogger(RRPolicyEndorsementQuoteAPITest.class);
   private PolicyQuoteTestUtil policyQuoteTestUtil;
   private PivotTestFixture pivotTestFixture;
   private GeoTestUtil geoTestUtil;
   private final String VENDOR_ID = "00014";
   private final String TEST_NAIC_NUMBER = "27065";
   private final String API_VERSION = "application/gov.fema.nfip.pivot-v2.0+json";
   private ItestTestUtil itestTestUtil;
   private boolean ignoreCertErrors = true;
   private final String quoteFile = "/PolicyQuoteFiles/SuccessFiles/genericQuoteRequest.json";
   private final String quoteFileRR = "/PolicyQuoteFiles/SuccessFiles/PolicyQuoteRequestP1.json";
   private final String quoteFileRR2 = "/PolicyQuoteFiles/SuccessFiles/PolicyQuoteRequestP2.json";
   private final String newBusinessRequestJson = "/PolicyQuoteFiles/SuccessFiles/rr2Policy.json";
   private final String newBusinessRequestJsonCorrection = "/PolicyQuoteFiles/SuccessFiles/PolicyQuoteRequestP2.json";
   private final String endorsementQuoteJson = "/PolicyQuoteFiles/SuccessFiles/endorsementQuoteRequest.json";
   private final LocalDate quoteEffectiveDate = LocalDate.of(2020,11,01);
   private final LocalDate policyEffectiveDate = LocalDate.of(2020,12,9);
   private final LocalDate transactionDate = policyEffectiveDate.minusDays(30);
   private final LocalDate expirationDate = policyEffectiveDate.plusYears(1);
   private final LocalDate endorsementEffDate = LocalDate.of(2021,01,01);
   private final String formattedQuoteEffDate = quoteEffectiveDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
   private final String formattedPolicyEffDate = policyEffectiveDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
   private final String formattedTransDate = transactionDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
   private final String formattedExpDate = expirationDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
   private final String formattedEndoQuoteEffDate = endorsementEffDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));

   private Set<Long> createdTestPolicyIds;

   @BeforeMethod(alwaysRun = true)
   public void logTestName(Method testMethod) {
       logger.info("Executing Test: " + testMethod.getName());
   }

   @BeforeClass(alwaysRun = true)
   public void setUp() {
       Configuration config = ConfigurationHelper.INSTANCE.getConfiguration();
       AuthProvider vendorAuthProvider = AuthHelper.getVendorAuthProvider(config, VENDOR_ID, 3600000L);
       policyQuoteTestUtil = new PolicyQuoteTestUtil(config, vendorAuthProvider, true);
       pivotTestFixture = new PivotTestFixture(config, AuthHelper.ITEST_AUTH, true);
       createdTestPolicyIds = new HashSet<>();
       itestTestUtil = new ItestTestUtil(config, "CAP",AuthHelper.ITEST_AUTH, ignoreCertErrors);
       geoTestUtil = new GeoTestUtil(config, AuthHelper.ITEST_AUTH, true);
       geoTestUtil.createTestCrsExclusions();
   }

   @AfterClass(alwaysRun = true)
   public void cleanUp(){
       pivotTestFixture.deletePolicies(createdTestPolicyIds);
   }


   // NFIPEAS-17620 Create a Policy Endorsement transaction
   @Test(groups = { "cap-api", "policy-quote", "policy-new-business" }, enabled = true)
   public void testRRPolicyEndorsementQuoteUponSuccessfulPolicy() {
       String quoteJson = "/PolicyQuoteFiles/SuccessFiles/genericNewBusinessQuoteRequest.json";
       String newBusinessRequestJson = "/PolicyQuoteFiles/SuccessFiles/rrNewBusinessPolicyRequest.json";
       String endorsementQuoteJson = "/PolicyQuoteFiles/SuccessFiles/endorsementQuoteRequest.json";
       LocalDate transactionDate = LocalDate.of(2021,3,10);
       //LocalDate effectiveDate = LocalDate.of(2021,4,10);;
       LocalDate effectiveDate = LocalDate.of(transactionDate.getYear(), transactionDate.getMonth().plus(1), transactionDate.getDayOfMonth());;
       LocalDate expirationDate = effectiveDate.plusYears(1);
       String formattedTransDate = transactionDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
       String formattedEffDate = effectiveDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
       String formattedExpDate = expirationDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
       String expectedQuoteTransactionType = "NEW_BUSINESS_QUOTE";
       SoftAssert softAssert = new SoftAssert();

       // Get a policy quote
       JSONObject quotePayload = createPolicyQuote(TEST_NAIC_NUMBER, transactionDate, quoteJson);
       JSONObject quoteResult = policyQuoteTestUtil.apiCreatePolicyQuote(quotePayload, API_VERSION);
       softAssert.assertEquals(quoteResult.get(CODE), 201, "Policy Quote Request result failed");
       String quoteUuid = quoteResult.get(TRANSACTION_ID).toString();

       //create a policy using the quote
       JSONObject newBusinessPayload = createPolicyUsingQuote(TEST_NAIC_NUMBER,quoteUuid,transactionDate);
       JSONObject rrNewBusinessResult = policyQuoteTestUtil.apiCreateNewBusinessPolicyRequest(newBusinessPayload, API_VERSION);
       softAssert.assertEquals(rrNewBusinessResult.get(CODE), 201, "Policy New Business result failed");
       String policyNumber = rrNewBusinessResult.getJSONObject(POLICY_OBJ).get(POLICY_NUM).toString();
       createdTestPolicyIds.add(Long.valueOf(rrNewBusinessResult.getJSONObject(POLICY_OBJ).get(ID).toString()));

       // create an endorsement for the newly created policy
       JSONObject endorsementQuotePayload = policyQuoteTestUtil.getJSONObjectFromJSONFile(endorsementQuoteJson);
       endorsementQuotePayload.put(POLICY_NUM, policyNumber);
       endorsementQuotePayload.put(EFF_DATE, formattedEffDate);
       endorsementQuotePayload.put(ENDORSEMENT_EFF_DATE, formattedEffDate);
       endorsementQuotePayload.put(NAIC_NUM, TEST_NAIC_NUMBER);
       JSONObject endorsementQuoteResult = policyQuoteTestUtil.apiCreatePolicyEndorsementQuote(endorsementQuotePayload, API_VERSION);
       softAssert.assertEquals(endorsementQuoteResult.get("message"), "Successful Endorsement Quote Request");
       softAssert.assertEquals(endorsementQuoteResult.get(CODE), 201, "Endorsement Quote Request result failed");
       softAssert.assertAll();
   }

   // NFIPEAS-17620 Create a Policy Endorsement transaction
   @Test(groups = { "cap-api", "policy-quote", "policy-new-business" }, enabled = true)
   public void testRejectWhenCbrsAndLatitudeDifferentInEndorsementQuote() {
       //verify that you get REJECT rule failure when the following fields values are different
       //than they are in original policy quote
       //CbrsOpaBuildingConsistentUse, cbrsBufferZoneDetermination, latitude, longitude

       String quoteJson = "/PolicyQuoteFiles/SuccessFiles/genericNewBusinessQuoteRequest.json";
       String endorsementQuoteJson = "/PolicyQuoteFiles/SuccessFiles/endorsementQuoteRequest.json";
       String newBusinessRequestJson = "/PolicyQuoteFiles/SuccessFiles/rrNewBusinessPolicyRequest.json";
       String latitudeUUId = "0c4109b2-c7ab-11eb-b8bc-0242ac130003";
       String longitudeUUId = "92896252-e978-4359-bc02-88c4277843e8";
       String cbrsOpaBuildingConsistentUseUUId = "28ae8a23-1ce2-42b4-a70c-78fa5cff8b83";
       String cbrsBufferZoneDeterminationUUId = "4a0c98df-d6e0-4d1f-a699-875ea6642ba7";

       LocalDate transactionDate = LocalDate.of(2021,3,10);
       LocalDate effectiveDate = LocalDate.of(2021,4,10);;
       LocalDate expirationDate = effectiveDate.plusYears(1);
       String formattedTransDate = transactionDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
       String formattedEffDate = effectiveDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
       String formattedExpDate = expirationDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
       String expectedQuoteTransactionType = "NEW_BUSINESS_QUOTE";
       SoftAssert softAssert = new SoftAssert();

       // Get a policy quote
       JSONObject quotePayload = createPolicyQuote(TEST_NAIC_NUMBER, transactionDate, quoteJson);
       JSONObject quoteResult = policyQuoteTestUtil.apiCreatePolicyQuote(quotePayload, API_VERSION);
       softAssert.assertEquals(quoteResult.get(CODE), 201, "Policy Quote Request result failed");
       String quoteUuid = quoteResult.get(TRANSACTION_ID).toString();

       //create a policy using the quote
       JSONObject newBusinessPayload = createPolicyUsingQuote(TEST_NAIC_NUMBER,quoteUuid,transactionDate);
       JSONObject rrNewBusinessResult = policyQuoteTestUtil.apiCreateNewBusinessPolicyRequest(newBusinessPayload, API_VERSION);
       softAssert.assertEquals(rrNewBusinessResult.get(CODE), 201, "Policy New Business result failed");
       String policyNumber = rrNewBusinessResult.getJSONObject(POLICY_OBJ).get(POLICY_NUM).toString();
       createdTestPolicyIds.add(Long.valueOf(rrNewBusinessResult.getJSONObject(POLICY_OBJ).get(ID).toString()));

       // create an endorsement quote for the newly created policy
       JSONObject endorsementQuotePayload = policyQuoteTestUtil.getJSONObjectFromJSONFile(endorsementQuoteJson);
       endorsementQuotePayload.put(POLICY_NUM, policyNumber);
       endorsementQuotePayload.put(EFF_DATE, formattedEffDate);
       endorsementQuotePayload.put(ENDORSEMENT_EFF_DATE, formattedEffDate);
       endorsementQuotePayload.put(NAIC_NUM, TEST_NAIC_NUMBER);
       endorsementQuotePayload.getJSONObject(PROP_CHARACTERISTICS_OBJ).getJSONObject(INDUSTRY_GEO_DETAILS_OBJ).put(CBRS_OPA_BUILDING_CONSISTENT_USE, "true");
       endorsementQuotePayload.getJSONObject(PROP_CHARACTERISTICS_OBJ).getJSONObject(INDUSTRY_GEO_DETAILS_OBJ).put(CBRS_BUFFER_ZONE_DETERMINATION, "true");
       endorsementQuotePayload.getJSONObject(PROP_CHARACTERISTICS_OBJ).getJSONObject(INDUSTRY_GEO_DETAILS_OBJ).put(LATITUDE, "3.593");
       endorsementQuotePayload.getJSONObject(PROP_CHARACTERISTICS_OBJ).getJSONObject(INDUSTRY_GEO_DETAILS_OBJ).put(LONGITUDE, "-15.036");
       JSONObject endorsementQuoteResult = null;

       try{
           endorsementQuoteResult = policyQuoteTestUtil.apiCreatePolicyEndorsementQuote(endorsementQuotePayload, API_VERSION);
       }catch(Exception e){
           endorsementQuoteResult = policyQuoteTestUtil.processRejectError(e.getMessage());
       }
       assertRuleFailureUUID(endorsementQuoteResult, latitudeUUId, true);
       assertRuleFailureUUID(endorsementQuoteResult, longitudeUUId, true);
       assertRuleFailureUUID(endorsementQuoteResult, cbrsOpaBuildingConsistentUseUUId, true);
       assertRuleFailureUUID(endorsementQuoteResult, cbrsBufferZoneDeterminationUUId, true);
       softAssert.assertAll();
   }

   // NFIPEAS-16837 Endorsement Quote Response JSON
   @Test(groups = { "cap-api", "policy-quote", "policy-new-business" }, enabled = true)
   public void testWarningWhenPropertyAddressDifferentInEndorsementQuote() {
       //verify that you get warning rule failure when the following fields values are different
       //that they are in original policy quote
       //system will trigger warning message and will create an endorsement quote using new property address
       // supplied via endorsement payload

       String quoteJson = "/PolicyQuoteFiles/SuccessFiles/genericNewBusinessQuoteRequest.json";
       String endorsementQuoteJson = "/PolicyQuoteFiles/SuccessFiles/endorsementQuoteRequest.json";
       String newBusinessRequestJson = "/PolicyQuoteFiles/SuccessFiles/rrNewBusinessPolicyRequest.json";
       LocalDate transactionDate = LocalDate.of(2021,3,10);
       LocalDate effectiveDate = LocalDate.of(2021,4,10);;
       LocalDate expirationDate = effectiveDate.plusYears(1);
       String formattedTransDate = transactionDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
       String formattedEffDate = effectiveDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
       String formattedExpDate = expirationDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
       String expectedQuoteTransactionType = "NEW_BUSINESS_QUOTE";
       String propertyAddressUUID = "f139b88f-fd20-4177-b5d4-dc53f1b98376";
       SoftAssert softAssert = new SoftAssert();

       // Get a policy quote
       JSONObject quotePayload = createPolicyQuote(TEST_NAIC_NUMBER, transactionDate, quoteJson);
       JSONObject quoteResult = policyQuoteTestUtil.apiCreatePolicyQuote(quotePayload, API_VERSION);
       softAssert.assertEquals(quoteResult.get(CODE), 201, "Policy Quote Request result failed");
       String quoteUuid = quoteResult.get(TRANSACTION_ID).toString();
       String origPropAddressBeginingStreetNumber = quoteResult.getJSONObject(RRE_QUOTE_OBJ).getJSONObject(POLICY_QUOTE_REQUEST_OBJ).getJSONObject(PROP_ADDRESS_OBJ).get(ADDR_LINE_1).toString();
       String origPropAddressLine1 = quoteResult.getJSONObject(RRE_QUOTE_OBJ).getJSONObject(POLICY_QUOTE_REQUEST_OBJ).getJSONObject(PROP_ADDRESS_OBJ).get(PROP_BEG_STREET_NUM).toString();
       JSONObject PropertyAddress =  quoteResult.getJSONObject(RRE_QUOTE_OBJ).getJSONObject(POLICY_QUOTE_REQUEST_OBJ).getJSONObject(PROP_ADDRESS_OBJ);

       //create a policy using the quote
       JSONObject newBusinessPayload = createPolicyUsingQuote(TEST_NAIC_NUMBER,quoteUuid,transactionDate);
       JSONObject rrNewBusinessResult = policyQuoteTestUtil.apiCreateNewBusinessPolicyRequest(newBusinessPayload, API_VERSION);
       softAssert.assertEquals(rrNewBusinessResult.get(CODE), 201, "Policy New Business result failed");
       String policyNumber = rrNewBusinessResult.getJSONObject(POLICY_OBJ).get(POLICY_NUM).toString();
       createdTestPolicyIds.add(Long.valueOf(rrNewBusinessResult.getJSONObject(POLICY_OBJ).get(ID).toString()));

       // create an endorsement quote for the newly created policy
       JSONObject endorsementQuotePayload = policyQuoteTestUtil.getJSONObjectFromJSONFile(endorsementQuoteJson);
       endorsementQuotePayload.put(POLICY_NUM, policyNumber);
       endorsementQuotePayload.put(EFF_DATE, formattedEffDate);
       endorsementQuotePayload.put(ENDORSEMENT_EFF_DATE, formattedEffDate);
       endorsementQuotePayload.put(NAIC_NUM, TEST_NAIC_NUMBER);
       endorsementQuotePayload.put(PROP_ADDRESS_OBJ,PropertyAddress);
       endorsementQuotePayload.getJSONObject(PROP_ADDRESS_OBJ).put(ADDR_LINE_1, "3500");
       endorsementQuotePayload.getJSONObject(PROP_ADDRESS_OBJ).put(PROP_BEG_STREET_NUM, "3500");
       JSONObject endorsementQuoteResult = policyQuoteTestUtil.apiCreatePolicyEndorsementQuote(endorsementQuotePayload, API_VERSION);
       softAssert.assertEquals(endorsementQuoteResult.get("message"), "Successful Endorsement Quote Request");

       // Assertions
       softAssert.assertEquals(endorsementQuoteResult.get(CODE), 201, "Endorsement Quote Request result failed");
       assertRuleFailureUUID(endorsementQuoteResult, propertyAddressUUID, true );
       String propAddressBeginningStreetNumberInEndorsementfound = endorsementQuoteResult.getJSONObject(RRE_QUOTE_OBJ).getJSONObject(POLICY_QUOTE_REQUEST_OBJ).getJSONObject(PROP_ADDRESS_OBJ).get(PROP_BEG_STREET_NUM).toString();
       String propAddressLine1InEndorsementfound  = endorsementQuoteResult.getJSONObject(RRE_QUOTE_OBJ).getJSONObject(POLICY_QUOTE_REQUEST_OBJ).getJSONObject(PROP_ADDRESS_OBJ).get(ADDR_LINE_1).toString();
       softAssert.assertNotEquals(propAddressBeginningStreetNumberInEndorsementfound, origPropAddressBeginingStreetNumber, PROP_BEG_STREET_NUM +  " are same and should not be same");
       softAssert.assertNotEquals(propAddressLine1InEndorsementfound, origPropAddressLine1, ADDR_LINE_1 +  " Address line 1 is same and should not be same");
       softAssert.assertEquals(propAddressLine1InEndorsementfound, "3500", ADDR_LINE_1 +  " incorrect");
       softAssert.assertEquals(propAddressBeginningStreetNumberInEndorsementfound, "3500", PROP_BEG_STREET_NUM +  " incorrect");
       softAssert.assertAll();
   }

   // NFIPEAS-17280 Create a Policy Endorsement transaction(Still under construction)
   @Test(groups = { "cap-api", "policy-quote", "policy-new-business" }, enabled = false)
   public void testRRPolicyEndorsementQuote() {
       String policyNumber = policyQuoteTestUtil.getTestPolicyNumber(TEST_NAIC_NUMBER);

       Map<String,String> expectedValues = new HashMap<>();
       expectedValues.put(FIRST_NAME, "Nobody");
       expectedValues.put(LAST_NAME, "Nohow");
       expectedValues.put(ADDR_LINE_1, "2900 A Lane");
       expectedValues.put(POLICY_NUM, policyNumber);
       expectedValues.put(PREMIUM_OBJ, "303");
       expectedValues.put(EFF_DATE, formattedEndoQuoteEffDate);
       expectedValues.put(EXP_DATE, formattedExpDate);
       expectedValues.put(CONDO_ASSOCIATION, "false");
       expectedValues.put(MOBILE_HOME_ID, "null");
       expectedValues.put(NUM_DETACHED_STRUCTURES, "null");
       expectedValues.put(NUM_ELEVATORS, "null");
       expectedValues.put(STATE_OWNED, "false");
       expectedValues.put(SMALL_BUSINESS, "true");
       expectedValues.put(NON_PROFIT, "true");

       // Get a policy quote
       JSONObject quotePayload = policyQuoteTestUtil.getJSONObjectFromJSONFile(quoteFile);
       quotePayload.put(NAIC_NUM, TEST_NAIC_NUMBER);
       quotePayload.put(EFF_DATE, formattedQuoteEffDate);
       JSONObject quoteResult = policyQuoteTestUtil.apiCreatePolicyQuote(quotePayload, API_VERSION);
       Assert.assertNotNull(quoteResult, "Policy Quote Request result was null");
       String quoteUuid = quoteResult.get(TRANSACTION_ID).toString();

       // Get a rr2 policy
       JSONObject rrNewBusinessResult = createRR2Policy(newBusinessRequestJson, policyNumber, formattedTransDate, formattedPolicyEffDate, formattedExpDate, quoteUuid);
       createdTestPolicyIds.add(Long.valueOf(rrNewBusinessResult.getJSONObject(POLICY_OBJ).get(ID).toString()));

       // Get endorsement quote
       JSONObject endorsementQuotePayload = policyQuoteTestUtil.getJSONObjectFromJSONFile(endorsementQuoteJson);
       endorsementQuotePayload.put(ENDORSEMENT_EFF_DATE, formattedEndoQuoteEffDate);
       endorsementQuotePayload.put(NAIC_NUM, TEST_NAIC_NUMBER);
       endorsementQuotePayload.put(POLICY_NUM, policyNumber);
       JSONObject endorsementQuoteResult = policyQuoteTestUtil.apiCreatePolicyEndorsementQuote(endorsementQuotePayload, API_VERSION);
       Assert.assertEquals(endorsementQuoteResult.get(CODE), HttpStatus.SC_CREATED, "Policy Quote Request result failed");
       String quoteUuid1 = endorsementQuoteResult.get(TRANSACTION_ID).toString();
   }

   // NFIPEAS-17224
   @Test(groups = { "cap-api", "policy-quote", "policy-new-business" }, enabled = true)
   public void testRREndorsementQuoteEndorsementEffectiveDate() {
       String endorsementQuoteJson = "/PolicyQuoteFiles/SuccessFiles/endorsementQuoteEffectiveDateRequest.json";
       String endorseBeforeEffectiveUuid = "2dce0cfa-c997-4431-ba6f-3d5aaacae8c7"; // endorsementEffectiveDate <date> cannot be before the policy effectiveDate <date>
       String endorseAfterExpireUuid = "8ab379fc-3637-4e02-82c4-ad4fcb18972f"; // endorsementEffectiveDate <date> cannot be on or after the policy expiration date <date>
       String policyNumber = policyQuoteTestUtil.getTestPolicyNumber(TEST_NAIC_NUMBER);
       LocalDate quoteEffDate = LocalDate.of(2020,11,1);
       LocalDate policyEffectiveDate = LocalDate.of(2020,12,9);
       LocalDate policyTransactionDate = LocalDate.of(2020,11,10);
       LocalDate endorsementQuoteEffectiveDate = LocalDate.of(2021,2,1);
       LocalDate endorseBeforePolicyEffectiveDate = LocalDate.of(2020,12,8);
       LocalDate endorseAfterPolicyExpireDate = policyEffectiveDate.plusDays(366);
       LocalDate policyExpirationDate = policyEffectiveDate.plusYears(1);
       String formattedPolicyEffDate = policyEffectiveDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
       String formattedPolicyTransDate = policyTransactionDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
       String formattedPolicyExpDate = policyExpirationDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
       String formattedEndoQuoteEffDate = endorsementQuoteEffectiveDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
       String formattedEndorseBeforeEffectiveDate = endorseBeforePolicyEffectiveDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
       String formattedEndorseAfterExpireDate = endorseAfterPolicyExpireDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));

       // Get a policy quote
       JSONObject quotePayload = policyQuoteTestUtil.getJSONObjectFromJSONFile(quoteFile);
       quotePayload.put(NAIC_NUM, TEST_NAIC_NUMBER);
       quotePayload.put(EFF_DATE, formattedPolicyEffDate);
       JSONObject quoteResult = policyQuoteTestUtil.apiCreatePolicyQuote(quotePayload, API_VERSION);
       Assert.assertNotNull(quoteResult, "Policy Quote Request result was null");
       String quoteUuid = quoteResult.get(TRANSACTION_ID).toString();

       // Get a rr2 policy
       JSONObject rrNewBusinessResult = createRR2Policy(newBusinessRequestJson, policyNumber, formattedPolicyTransDate, formattedPolicyEffDate, formattedPolicyExpDate, quoteUuid);

      // Rule failures. policyNumber and endorsementEffectiveDate are required fields.
       JSONObject endorsementQuotePayload = policyQuoteTestUtil.getJSONObjectFromJSONFile(endorsementQuoteJson);
       endorsementQuotePayload.put(ENDORSEMENT_EFF_DATE, JSONObject.NULL);
       endorsementQuotePayload.put(NAIC_NUM, TEST_NAIC_NUMBER);
       endorsementQuotePayload.put(POLICY_NUM, JSONObject.NULL);
       try{
           policyQuoteTestUtil.apiCreatePolicyEndorsementQuote(endorsementQuotePayload, API_VERSION);
           Assert.fail("Policy quote created successfully when it wasn't supposed to");
       }catch(Exception e){
           Assert.assertTrue(e.toString().contains("endorsementEffectiveDate"), "Expected a field error for endorsementEffectiveDate");
           Assert.assertTrue(e.toString().contains("policyNumber"), "Expected a field error for policyNumber");
       }

     // Rule failures. Set endorsementEffectiveDate before policyEffectiveDate
       JSONObject endorsementBeforeEffectiveQuotePayload = policyQuoteTestUtil.getJSONObjectFromJSONFile(endorsementQuoteJson);
       endorsementBeforeEffectiveQuotePayload.put(ENDORSEMENT_EFF_DATE, formattedEndorseBeforeEffectiveDate);
       endorsementBeforeEffectiveQuotePayload.put(NAIC_NUM, TEST_NAIC_NUMBER);
       endorsementBeforeEffectiveQuotePayload.put(POLICY_NUM, policyNumber);
       try{
           policyQuoteTestUtil.apiCreatePolicyEndorsementQuote(endorsementBeforeEffectiveQuotePayload, API_VERSION);
           Assert.fail("Policy quote created successfully when it wasn't supposed to");
       }catch(Exception e){
           Assert.assertTrue(e.toString().contains(endorseBeforeEffectiveUuid), "Expected rule failure endorsementEffectiveDate cannot be before the policy effectiveDate is not showing");
       }

       // Rule failures. Set endorsementEffectiveDate after policy expiration date
       JSONObject endorsementAfterExpirePayload = policyQuoteTestUtil.getJSONObjectFromJSONFile(endorsementQuoteJson);
       endorsementAfterExpirePayload.put(ENDORSEMENT_EFF_DATE, formattedEndorseAfterExpireDate);
       endorsementAfterExpirePayload.put(NAIC_NUM, TEST_NAIC_NUMBER);
       endorsementAfterExpirePayload.put(POLICY_NUM, policyNumber);
       try{
           policyQuoteTestUtil.apiCreatePolicyEndorsementQuote(endorsementAfterExpirePayload, API_VERSION);
           Assert.fail("Policy quote created successfully when it wasn't supposed to");
       }catch(Exception e){
           Assert.assertTrue(e.toString().contains(endorseAfterExpireUuid), "Expected rule failure endorsementEffectiveDate cannot be on or after the policy expiration date is not showing");
       }

       // Get policy endorsement quote
       JSONObject endorsementQuotePayload3 = policyQuoteTestUtil.getJSONObjectFromJSONFile(endorsementQuoteJson);
       endorsementQuotePayload3.put(NAIC_NUM, TEST_NAIC_NUMBER);
       endorsementQuotePayload3.put(POLICY_NUM, policyNumber);
       endorsementQuotePayload3.put(ENDORSEMENT_EFF_DATE, formattedEndoQuoteEffDate);
       JSONObject endorsementQuoteResult = policyQuoteTestUtil.apiCreatePolicyEndorsementQuote(endorsementQuotePayload3, API_VERSION);
       Assert.assertEquals(endorsementQuoteResult.get(CODE), HttpStatus.SC_CREATED, "Policy Quote Request result failed");
       String endorsementQuoteUuid = endorsementQuoteResult.get(TRANSACTION_ID).toString();
   }

   // NFIPEAS-17622
   @Test(groups = { "cap-api", "policy-quote", "policy-new-business" }, enabled = true)
   public void testRREndorsementQuoteNewlyMappedDiscountFactor() {
       LocalDate newlyMappedDate = LocalDate.of(2017,9,1);
       String policyNumber = policyQuoteTestUtil.getTestPolicyNumber(TEST_NAIC_NUMBER);
       String formattedNewlyMappedDate = newlyMappedDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
       String expectedNewlyMappedStatutoryDiscountType = "NEWLY_MAPPED_DISCOUNT_FACTOR";
       String expectedNewlyMappedStatutoryPremiumIncreaseCap = "NEWLY_MAPPED_FACTOR";

       // Get a policy quote
       JSONObject quotePayload = policyQuoteTestUtil.getJSONObjectFromJSONFile(quoteFile);
       quotePayload.put(NAIC_NUM, TEST_NAIC_NUMBER);
       quotePayload.put(EFF_DATE, formattedQuoteEffDate);
       JSONObject quoteResult = policyQuoteTestUtil.apiCreatePolicyQuote(quotePayload, API_VERSION);
       Assert.assertNotNull(quoteResult, "Policy Quote Request result was null");
       String quoteUuid = quoteResult.get(TRANSACTION_ID).toString();

       // Get a rr2 policy
       JSONObject rrNewBusinessResult = createRR2Policy(newBusinessRequestJson, policyNumber, formattedTransDate, formattedPolicyEffDate, formattedExpDate, quoteUuid);
       createdTestPolicyIds.add(Long.valueOf(rrNewBusinessResult.getJSONObject(POLICY_OBJ).get(ID).toString()));

       // Get endorsement quote and statutoryDiscountType  = "NEWLY_MAPPED_DISCOUNT_FACTOR"
       JSONObject endorsementQuotePayload = policyQuoteTestUtil.getJSONObjectFromJSONFile(endorsementQuoteJson);
       endorsementQuotePayload.put(ENDORSEMENT_EFF_DATE, formattedEndoQuoteEffDate);
       endorsementQuotePayload.put(NAIC_NUM, TEST_NAIC_NUMBER);
       endorsementQuotePayload.put(POLICY_NUM, policyNumber);
       endorsementQuotePayload.getJSONObject(PROP_CHARACTERISTICS_OBJ).put(NEWLY_MAPPED_DATE,formattedNewlyMappedDate);
       endorsementQuotePayload.getJSONObject(PROP_CHARACTERISTICS_OBJ).put(NEWLY_MAPPED_DISC_ELIGIBLE,true);
       endorsementQuotePayload.getJSONObject(PROP_CHARACTERISTICS_OBJ).put(NEWLY_MAPPED, true);
       JSONObject endorsementQuoteResult = policyQuoteTestUtil.apiCreatePolicyEndorsementQuote(endorsementQuotePayload, API_VERSION);
       Assert.assertEquals(endorsementQuoteResult.get(CODE), HttpStatus.SC_CREATED, "Policy Quote Request result failed");
       assertStatutoryDiscountType(endorsementQuoteResult, expectedNewlyMappedStatutoryDiscountType);
       assertStatutoryPremiumIncreaseCap(endorsementQuoteResult, expectedNewlyMappedStatutoryPremiumIncreaseCap);
       String quoteUuid1 = endorsementQuoteResult.get(TRANSACTION_ID).toString();
   }

   // NFIPEAS-17622
   @Test(groups = { "cap-api", "policy-quote", "policy-new-business" }, enabled = true)
   public void testRREndorsementQuoteEmergencyProgramDiscountFactor() {
       String policyNumber = policyQuoteTestUtil.getTestPolicyNumber(TEST_NAIC_NUMBER);
       String expectedEmergencyProgramStatutoryDiscountType = "EMERGENCY_PROGRAM_DISCOUNT_FACTOR";
       String expectedEmergencyStatutoryPremiumIncreaseCap = "PREFIRM_DISCOUNT_NON_PRIMARY"; // primaryResidence = false
       String expectedEmergencyStatutoryPremiumIncreaseCap1 = "PREFIRM_DISCOUNT_PRIMARY"; // primaryResidence = true

       // Get a policy quote. emergencyProgram =  true
       JSONObject quotePayload = policyQuoteTestUtil.getJSONObjectFromJSONFile(quoteFile);
       quotePayload.put(NAIC_NUM, TEST_NAIC_NUMBER);
       quotePayload.put(EFF_DATE, formattedQuoteEffDate);
       quotePayload.getJSONObject(COVERAGE_AMOUNTS_OBJ).put(TOTAL_COVERAGE_BUILDING, 25000);
       quotePayload.getJSONObject(COVERAGE_AMOUNTS_OBJ).put(TOTAL_COVERAGE_CONTENTS, 10000);
       quotePayload.getJSONObject(COVERAGE_AMOUNTS_OBJ).put(BUILDING_DEDUCTIBLE, "2");
       quotePayload.getJSONObject(COVERAGE_AMOUNTS_OBJ).put(CONTENTS_DEDUCTIBLE, "2");
       quotePayload.getJSONObject(PROP_CHARACTERISTICS_OBJ).getJSONObject(INDUSTRY_GEO_DETAILS_OBJ).put(EMERGENCY_PROGRAM, true);
       JSONObject quoteResult = policyQuoteTestUtil.apiCreatePolicyQuote(quotePayload, API_VERSION);
       Assert.assertNotNull(quoteResult, "Policy Quote Request result was null");
       String quoteUuid = quoteResult.get(TRANSACTION_ID).toString();

       // Get a rr2 policy
       JSONObject rrNewBusinessResult = createRR2Policy(newBusinessRequestJson, policyNumber, formattedTransDate, formattedPolicyEffDate, formattedExpDate, quoteUuid);
       createdTestPolicyIds.add(Long.valueOf(rrNewBusinessResult.getJSONObject(POLICY_OBJ).get(ID).toString()));

       // Get endorsement quote and statutoryDiscountType  = "EMERGENCY_PROGRAM_DISCOUNT_FACTOR", emergencyProgram = true and primaryResidence = false
       JSONObject endorsementQuotePayload = policyQuoteTestUtil.getJSONObjectFromJSONFile(endorsementQuoteJson);
       endorsementQuotePayload.put(ENDORSEMENT_EFF_DATE, formattedEndoQuoteEffDate);
       endorsementQuotePayload.put(NAIC_NUM, TEST_NAIC_NUMBER);
       endorsementQuotePayload.put(POLICY_NUM, policyNumber);
       endorsementQuotePayload.getJSONObject(COVERAGE_AMOUNTS_OBJ).put(TOTAL_COVERAGE_BUILDING, 25000);
       endorsementQuotePayload.getJSONObject(COVERAGE_AMOUNTS_OBJ).put(TOTAL_COVERAGE_CONTENTS, 10000);
       endorsementQuotePayload.getJSONObject(COVERAGE_AMOUNTS_OBJ).put(BUILDING_DEDUCTIBLE, "2");
       endorsementQuotePayload.getJSONObject(COVERAGE_AMOUNTS_OBJ).put(CONTENTS_DEDUCTIBLE, "2");
       endorsementQuotePayload.getJSONObject(PROP_CHARACTERISTICS_OBJ).put(PRIMARY_RESIDENCE, false);
       endorsementQuotePayload.getJSONObject(PROP_CHARACTERISTICS_OBJ).getJSONObject(INDUSTRY_GEO_DETAILS_OBJ).put(EMERGENCY_PROGRAM, true);
       JSONObject endorsementQuoteResult = policyQuoteTestUtil.apiCreatePolicyEndorsementQuote(endorsementQuotePayload, API_VERSION);
       Assert.assertEquals(endorsementQuoteResult.get(CODE), HttpStatus.SC_CREATED, "Policy Quote Request result failed");
       assertStatutoryDiscountType(endorsementQuoteResult, expectedEmergencyProgramStatutoryDiscountType);
       assertStatutoryPremiumIncreaseCap(endorsementQuoteResult, expectedEmergencyStatutoryPremiumIncreaseCap);
       String quoteUuid1 = endorsementQuoteResult.get(TRANSACTION_ID).toString();

       // Get endorsement quote and statutoryDiscountType  = "EMERGENCY_PROGRAM_DISCOUNT_FACTOR", emergencyProgram = true and primaryResidence = true
       JSONObject primaryEndorsementQuotePayload = policyQuoteTestUtil.getJSONObjectFromJSONFile(endorsementQuoteJson);
       primaryEndorsementQuotePayload.put(ENDORSEMENT_EFF_DATE, formattedEndoQuoteEffDate);
       primaryEndorsementQuotePayload.put(NAIC_NUM, TEST_NAIC_NUMBER);
       primaryEndorsementQuotePayload.put(POLICY_NUM, policyNumber);
       primaryEndorsementQuotePayload.getJSONObject(COVERAGE_AMOUNTS_OBJ).put(TOTAL_COVERAGE_BUILDING, 25000);
       primaryEndorsementQuotePayload.getJSONObject(COVERAGE_AMOUNTS_OBJ).put(TOTAL_COVERAGE_CONTENTS, 10000);
       primaryEndorsementQuotePayload.getJSONObject(COVERAGE_AMOUNTS_OBJ).put(BUILDING_DEDUCTIBLE, "2");
       primaryEndorsementQuotePayload.getJSONObject(COVERAGE_AMOUNTS_OBJ).put(CONTENTS_DEDUCTIBLE, "2");
       primaryEndorsementQuotePayload.getJSONObject(PROP_CHARACTERISTICS_OBJ).put(PRIMARY_RESIDENCE, true);
       primaryEndorsementQuotePayload.getJSONObject(PROP_CHARACTERISTICS_OBJ).getJSONObject(INDUSTRY_GEO_DETAILS_OBJ).put(EMERGENCY_PROGRAM, true);
       JSONObject primaryEndorsementQuoteResult = policyQuoteTestUtil.apiCreatePolicyEndorsementQuote(primaryEndorsementQuotePayload, API_VERSION);
       Assert.assertEquals(primaryEndorsementQuoteResult.get(CODE), HttpStatus.SC_CREATED, "Policy Quote Request result failed");
       assertStatutoryDiscountType(primaryEndorsementQuoteResult, expectedEmergencyProgramStatutoryDiscountType);
       assertStatutoryPremiumIncreaseCap(primaryEndorsementQuoteResult, expectedEmergencyStatutoryPremiumIncreaseCap1);
       String quoteUuid2 = primaryEndorsementQuoteResult.get(TRANSACTION_ID).toString();
   }

   // NFIPEAS-17189
   /*
   * If the Endorsement Quote API is used, "policy" quoteTransactionType = ENDORSEMENT_QUOTE
   * */

   @Test(groups = { "cap-api", "policy-quote", "policy-new-business" }, enabled = true)
   public void testPolicyEndorsement() {
       // Create Policy Number
       String policyNumber = policyQuoteTestUtil.getTestPolicyNumber(TEST_NAIC_NUMBER);
       JSONObject quotePayload = policyQuoteTestUtil.getJSONObjectFromJSONFile(quoteFileRR);
       quotePayload.put(NAIC_NUM, TEST_NAIC_NUMBER);
       quotePayload.put(EFF_DATE, formattedQuoteEffDate);
       quotePayload.getJSONObject(COVERAGE_AMOUNTS_OBJ).put(TOTAL_COVERAGE_BUILDING, 25000);
       quotePayload.getJSONObject(COVERAGE_AMOUNTS_OBJ).put(TOTAL_COVERAGE_CONTENTS, 10000);
       quotePayload.getJSONObject(COVERAGE_AMOUNTS_OBJ).put(BUILDING_DEDUCTIBLE, "2");
       quotePayload.getJSONObject(COVERAGE_AMOUNTS_OBJ).put(CONTENTS_DEDUCTIBLE, "2");
       quotePayload.getJSONObject(PROP_CHARACTERISTICS_OBJ).getJSONObject(INDUSTRY_GEO_DETAILS_OBJ).put(EMERGENCY_PROGRAM, true);
       JSONObject quoteResult = policyQuoteTestUtil.apiCreatePolicyQuote(quotePayload, API_VERSION);
       // Verifying the policy number
       Assert.assertNotNull(quoteResult, "Policy Quote Request result was null");
       String quoteUuid = quoteResult.get(TRANSACTION_ID).toString();
       //Passing the policy number
       JSONObject rrNewBusinessResult = createRR2Policy(newBusinessRequestJson, policyNumber, formattedTransDate, formattedPolicyEffDate, formattedExpDate, quoteUuid);
       createdTestPolicyIds.add(Long.valueOf(rrNewBusinessResult.getJSONObject(POLICY_OBJ).get(ID).toString()));
       // Endorsement quote payload
       JSONObject endorsementQuotePayload = policyQuoteTestUtil.getJSONObjectFromJSONFile(endorsementQuoteJson);
       endorsementQuotePayload.put(ENDORSEMENT_EFF_DATE, formattedEndoQuoteEffDate);
       endorsementQuotePayload.put(NAIC_NUM, TEST_NAIC_NUMBER);
       endorsementQuotePayload.put(POLICY_NUM, policyNumber);
       endorsementQuotePayload.getJSONObject(COVERAGE_AMOUNTS_OBJ).put(TOTAL_COVERAGE_BUILDING, 25000);
       endorsementQuotePayload.getJSONObject(COVERAGE_AMOUNTS_OBJ).put(TOTAL_COVERAGE_CONTENTS, 10000);
       endorsementQuotePayload.getJSONObject(COVERAGE_AMOUNTS_OBJ).put(BUILDING_DEDUCTIBLE, "2");
       endorsementQuotePayload.getJSONObject(COVERAGE_AMOUNTS_OBJ).put(CONTENTS_DEDUCTIBLE, "2");
       endorsementQuotePayload.getJSONObject(PROP_CHARACTERISTICS_OBJ).put(PRIMARY_RESIDENCE, false);
       endorsementQuotePayload.getJSONObject(PROP_CHARACTERISTICS_OBJ).getJSONObject(INDUSTRY_GEO_DETAILS_OBJ).put(EMERGENCY_PROGRAM, true);
       JSONObject endorsementQuoteResult = policyQuoteTestUtil.apiCreatePolicyEndorsementQuote(endorsementQuotePayload, API_VERSION);
      // Verifying the status code
       Assert.assertEquals(endorsementQuoteResult.get(CODE), HttpStatus.SC_CREATED, "Policy Quote Request result failed");
       String quoteUuid1 = endorsementQuoteResult.get(TRANSACTION_ID).toString();
       // Get endorsement quote and statutoryDiscountType  = "EMERGENCY_PROGRAM_DISCOUNT_FACTOR", emergencyProgram = true and primaryResidence = true
       JSONObject primaryEndorsementQuotePayload = policyQuoteTestUtil.getJSONObjectFromJSONFile(endorsementQuoteJson);
       primaryEndorsementQuotePayload.put(ENDORSEMENT_EFF_DATE, formattedEndoQuoteEffDate);
       primaryEndorsementQuotePayload.put(NAIC_NUM, TEST_NAIC_NUMBER);
       primaryEndorsementQuotePayload.put(POLICY_NUM, policyNumber);
       primaryEndorsementQuotePayload.getJSONObject(COVERAGE_AMOUNTS_OBJ).put(TOTAL_COVERAGE_BUILDING, 25000);
       primaryEndorsementQuotePayload.getJSONObject(COVERAGE_AMOUNTS_OBJ).put(TOTAL_COVERAGE_CONTENTS, 10000);
       primaryEndorsementQuotePayload.getJSONObject(COVERAGE_AMOUNTS_OBJ).put(BUILDING_DEDUCTIBLE, "2");
       primaryEndorsementQuotePayload.getJSONObject(COVERAGE_AMOUNTS_OBJ).put(CONTENTS_DEDUCTIBLE, "2");
       primaryEndorsementQuotePayload.getJSONObject(PROP_CHARACTERISTICS_OBJ).put(PRIMARY_RESIDENCE, true);
       primaryEndorsementQuotePayload.getJSONObject(PROP_CHARACTERISTICS_OBJ).getJSONObject(INDUSTRY_GEO_DETAILS_OBJ).put(EMERGENCY_PROGRAM, true);
      // Creating Endorsement Quote
       JSONObject primaryEndorsementQuoteResult = policyQuoteTestUtil.apiCreatePolicyEndorsementQuote(primaryEndorsementQuotePayload, API_VERSION);
       // Verifying status code
       Assert.assertEquals(primaryEndorsementQuoteResult.get(CODE), HttpStatus.SC_CREATED, "Policy Quote Request result failed");
       Assert.assertEquals(primaryEndorsementQuoteResult.get("message"),"Successful Endorsement Quote Request","Verifying endorsement message");
       JSONObject endorsementType=(JSONObject)primaryEndorsementQuoteResult.get("rreQuote");
     // Verifying quote request message
       Assert.assertEquals(endorsementType.get("quoteTransactionType"), "ENDORSEMENT_QUOTE", "Verify ENDORSEMENT_QUOTE");
   }

   @Test(groups = { "cap-api", "policy-quote", "policy-new-business" }, enabled = true)
   public void testPolicyEndorsement2() {
       // Create Policy Number
       String policyNumber = policyQuoteTestUtil.getTestPolicyNumber(TEST_NAIC_NUMBER);
       JSONObject quotePayload = policyQuoteTestUtil.getJSONObjectFromJSONFile(newBusinessRequestJsonCorrection);
      String effectiveDate=quotePayload.getJSONObject("rrPolicyPeriod").getString("effectiveDate");
       String expirationDate=quotePayload.getJSONObject("rrPolicyPeriod").getString("expirationDate");
       String applicationDate=quotePayload.getJSONObject("rrPolicyPeriod").getString("applicationDate");

       quotePayload.put(NAIC_NUM, TEST_NAIC_NUMBER);
       quotePayload.put(EFF_DATE, formattedQuoteEffDate);
     //  quotePayload.getJSONObject(COVERAGE_AMOUNTS_OBJ).put(TOTAL_COVERAGE_BUILDING, 25000);
      // quotePayload.getJSONObject(COVERAGE_AMOUNTS_OBJ).put(TOTAL_COVERAGE_CONTENTS, 10000);
      // quotePayload.getJSONObject(COVERAGE_AMOUNTS_OBJ).put(BUILDING_DEDUCTIBLE, "2");
     //  quotePayload.getJSONObject(COVERAGE_AMOUNTS_OBJ).put(CONTENTS_DEDUCTIBLE, "2");
    //   quotePayload.getJSONObject(PROP_CHARACTERISTICS_OBJ).getJSONObject(INDUSTRY_GEO_DETAILS_OBJ).put(EMERGENCY_PROGRAM, true);
      // JSONObject quoteResult = policyQuoteTestUtil.apiCreatePolicyQuote(quotePayload, API_VERSION);
       // Verifying the policy number
      // Assert.assertNotNull(quoteResult, "Policy Quote Request result was null");
      // String quoteUuid = quoteResult.get(TRANSACTION_ID).toString();
    //   String quoteUuidP2 = null;
       //Passing the policy number
     JSONObject rrNewBusinessResult = createRR2Policy(newBusinessRequestJsonCorrection, policyNumber,applicationDate, effectiveDate,expirationDate,null);
     // JSONObject rrNewBusinessResult = createRR3Policy(newBusinessRequestJsonCorrection, policyNumber,"12/01/2020","04/10/2021" , "04/10/2022","null");
       createdTestPolicyIds.add(Long.valueOf(rrNewBusinessResult.getJSONObject(POLICY_OBJ).get(ID).toString()));
       // Endorsement quote payload
       JSONObject endorsementQuotePayload = policyQuoteTestUtil.getJSONObjectFromJSONFile(endorsementQuoteJson);
       endorsementQuotePayload.put(ENDORSEMENT_EFF_DATE, formattedEndoQuoteEffDate);
       endorsementQuotePayload.put(NAIC_NUM, TEST_NAIC_NUMBER);
       endorsementQuotePayload.put(POLICY_NUM, policyNumber);
       endorsementQuotePayload.put(QUOTE_UUID,JSONObject.NULL);
       endorsementQuotePayload.getJSONObject(COVERAGE_AMOUNTS_OBJ).put(TOTAL_COVERAGE_BUILDING, 25000);
       endorsementQuotePayload.getJSONObject(COVERAGE_AMOUNTS_OBJ).put(TOTAL_COVERAGE_CONTENTS, 10000);
       endorsementQuotePayload.getJSONObject(COVERAGE_AMOUNTS_OBJ).put(BUILDING_DEDUCTIBLE, "2");
       endorsementQuotePayload.getJSONObject(COVERAGE_AMOUNTS_OBJ).put(CONTENTS_DEDUCTIBLE, "2");
       endorsementQuotePayload.getJSONObject(PROP_CHARACTERISTICS_OBJ).put(PRIMARY_RESIDENCE, false);
       endorsementQuotePayload.getJSONObject(PROP_CHARACTERISTICS_OBJ).getJSONObject(INDUSTRY_GEO_DETAILS_OBJ).put(EMERGENCY_PROGRAM, true);
       JSONObject endorsementQuoteResult = policyQuoteTestUtil.apiCreatePolicyEndorsementQuote(endorsementQuotePayload, API_VERSION);
       // Verifying the status code
       Assert.assertEquals(endorsementQuoteResult.get(CODE), HttpStatus.SC_CREATED, "Policy Quote Request result failed");
       String quoteUuid1 = endorsementQuoteResult.get(TRANSACTION_ID).toString();
       // Get endorsement quote and statutoryDiscountType  = "EMERGENCY_PROGRAM_DISCOUNT_FACTOR", emergencyProgram = true and primaryResidence = true
       JSONObject primaryEndorsementQuotePayload = policyQuoteTestUtil.getJSONObjectFromJSONFile(endorsementQuoteJson);
       primaryEndorsementQuotePayload.put(ENDORSEMENT_EFF_DATE, formattedEndoQuoteEffDate);
       primaryEndorsementQuotePayload.put(NAIC_NUM, TEST_NAIC_NUMBER);
       primaryEndorsementQuotePayload.put(POLICY_NUM, policyNumber);
       primaryEndorsementQuotePayload.getJSONObject(COVERAGE_AMOUNTS_OBJ).put(TOTAL_COVERAGE_BUILDING, 25000);
       primaryEndorsementQuotePayload.getJSONObject(COVERAGE_AMOUNTS_OBJ).put(TOTAL_COVERAGE_CONTENTS, 10000);
       primaryEndorsementQuotePayload.getJSONObject(COVERAGE_AMOUNTS_OBJ).put(BUILDING_DEDUCTIBLE, "2");
       primaryEndorsementQuotePayload.getJSONObject(COVERAGE_AMOUNTS_OBJ).put(CONTENTS_DEDUCTIBLE, "2");
       primaryEndorsementQuotePayload.getJSONObject(PROP_CHARACTERISTICS_OBJ).put(PRIMARY_RESIDENCE, true);
       primaryEndorsementQuotePayload.getJSONObject(PROP_CHARACTERISTICS_OBJ).getJSONObject(INDUSTRY_GEO_DETAILS_OBJ).put(EMERGENCY_PROGRAM, true);
       // Creating Endorsement Quote
       JSONObject primaryEndorsementQuoteResult = policyQuoteTestUtil.apiCreatePolicyEndorsementQuote(primaryEndorsementQuotePayload, API_VERSION);
       // Verifying status code
       Assert.assertEquals(primaryEndorsementQuoteResult.get(CODE), HttpStatus.SC_CREATED, "Policy Quote Request result failed");
       Assert.assertEquals(primaryEndorsementQuoteResult.get("message"),"Successful Endorsement Quote Request","Verifying endorsement message");
       Assert.assertEquals(primaryEndorsementQuoteResult.get("policyTransactionType"),"CORRECTION","Verifying policyTransactionType");
       JSONObject endorsementType=(JSONObject)primaryEndorsementQuoteResult.get("rreQuote");
       // Verifying quote request message
       Assert.assertEquals(endorsementType.get("quoteTransactionType"), "ENDORSEMENT_QUOTE", "Verify ENDORSEMENT_QUOTE");
   }


   // NFIPEAS-17622
   @Test(groups = { "cap-api", "policy-quote", "policy-new-business" }, enabled = true)
   public void testRREndorsementQuoteARFloodZoneDiscountFactor() {
       String policyNumber = policyQuoteTestUtil.getTestPolicyNumber(TEST_NAIC_NUMBER);
       String expectedARFloodZoneStatutoryDiscountType = "AR_FLOOD_ZONE_DISCOUNT_FACTOR";
       String expectedARFloodZoneStatutoryPremiumIncreaseCap = "STANDARD";

       // Get a policy quote
       JSONObject quotePayload = policyQuoteTestUtil.getJSONObjectFromJSONFile(quoteFile);
       quotePayload.put(NAIC_NUM, TEST_NAIC_NUMBER);
       quotePayload.put(EFF_DATE, formattedQuoteEffDate);
       JSONObject quoteResult = policyQuoteTestUtil.apiCreatePolicyQuote(quotePayload, API_VERSION);
       Assert.assertNotNull(quoteResult, "Policy Quote Request result was null");
       String quoteUuid = quoteResult.get(TRANSACTION_ID).toString();

       // Get a rr2 policy
       JSONObject rrNewBusinessResult = createRR2Policy(newBusinessRequestJson, policyNumber, formattedTransDate, formattedPolicyEffDate, formattedExpDate, quoteUuid);
       createdTestPolicyIds.add(Long.valueOf(rrNewBusinessResult.getJSONObject(POLICY_OBJ).get(ID).toString()));

       // Get endorsement quote and statutoryDiscountType  = "AR_FLOOD_ZONE_DISCOUNT_FACTOR"
       JSONObject endorsementQuotePayload = policyQuoteTestUtil.getJSONObjectFromJSONFile(endorsementQuoteJson);
       endorsementQuotePayload.put(ENDORSEMENT_EFF_DATE, formattedEndoQuoteEffDate);
       endorsementQuotePayload.put(NAIC_NUM, TEST_NAIC_NUMBER);
       endorsementQuotePayload.put(POLICY_NUM, policyNumber);
       endorsementQuotePayload.getJSONObject(PROP_CHARACTERISTICS_OBJ).getJSONObject(INDUSTRY_GEO_DETAILS_OBJ).put(EMERGENCY_PROGRAM, false);
       endorsementQuotePayload.getJSONObject(PROP_CHARACTERISTICS_OBJ).getJSONObject(INDUSTRY_GEO_DETAILS_OBJ).put(FLOOD_ZONE, "AR");
       JSONObject endorsementQuoteResult = policyQuoteTestUtil.apiCreatePolicyEndorsementQuote(endorsementQuotePayload, API_VERSION);
       Assert.assertEquals(endorsementQuoteResult.get(CODE), HttpStatus.SC_CREATED, "Policy Quote Request result failed");
       assertStatutoryDiscountType(endorsementQuoteResult, expectedARFloodZoneStatutoryDiscountType);
       assertStatutoryPremiumIncreaseCap(endorsementQuoteResult, expectedARFloodZoneStatutoryPremiumIncreaseCap);
       String quoteUuid1 = endorsementQuoteResult.get(TRANSACTION_ID).toString();
   }

   // NFIPEAS-17622
   @Test(groups = { "cap-api", "policy-quote", "policy-new-business" }, enabled = true)
   public void testRREndorsementQuotePreFirmDiscountFactor() {
       String policyNumber = policyQuoteTestUtil.getTestPolicyNumber(TEST_NAIC_NUMBER);
       String expectedPreFirmStatutoryDiscountType = "NEW_CUSTOMER_PREFIRM_DISCOUNT_FACTOR";
       String expectedPreFirmStatutoryPremiumIncreaseCap = "PREFIRM_DISCOUNT_PRIMARY";

       // Get a policy quote, preFirmSubsidyEligible = false
       JSONObject quotePayload = policyQuoteTestUtil.getJSONObjectFromJSONFile(quoteFile);
       quotePayload.put(NAIC_NUM, TEST_NAIC_NUMBER);
       quotePayload.put(EFF_DATE, formattedQuoteEffDate);
       JSONObject quoteResult = policyQuoteTestUtil.apiCreatePolicyQuote(quotePayload, API_VERSION);
       Assert.assertNotNull(quoteResult, "Policy Quote Request result was null");
       String quoteUuid = quoteResult.get(TRANSACTION_ID).toString();

       // Get a rr2 policy
       JSONObject rrNewBusinessResult = createRR2Policy(newBusinessRequestJson, policyNumber, formattedTransDate, formattedPolicyEffDate, formattedExpDate, quoteUuid);
       createdTestPolicyIds.add(Long.valueOf(rrNewBusinessResult.getJSONObject(POLICY_OBJ).get(ID).toString()));

       // Get endorsement quote . preFirmSubsidyEligible = true , primaryResidence = true then
       // statutoryDiscountType  = "NEW_CUSTOMER_PREFIRM_DISCOUNT_FACTOR" and statutoryPremiumIncreaseCap = PREFIRM_DISCOUNT_PRIMARY"
       JSONObject endorsementQuotePayload = policyQuoteTestUtil.getJSONObjectFromJSONFile(endorsementQuoteJson);
       endorsementQuotePayload.put(ENDORSEMENT_EFF_DATE, formattedEndoQuoteEffDate);
       endorsementQuotePayload.put(NAIC_NUM, TEST_NAIC_NUMBER);
       endorsementQuotePayload.put(POLICY_NUM, policyNumber);
       endorsementQuotePayload.getJSONObject(PROP_CHARACTERISTICS_OBJ).put(PREFIRM_SUBSIDY_ELIGIBLE, true);
       endorsementQuotePayload.getJSONObject(PROP_CHARACTERISTICS_OBJ).put(POST_FIRM_CONSTRUCTION, false);
       endorsementQuotePayload.getJSONObject(PROP_CHARACTERISTICS_OBJ).put(PRIMARY_RESIDENCE,true);
       JSONObject endorsementQuoteResult = policyQuoteTestUtil.apiCreatePolicyEndorsementQuote(endorsementQuotePayload, API_VERSION);
       Assert.assertEquals(endorsementQuoteResult.get(CODE), HttpStatus.SC_CREATED, "Policy Quote Request result failed");
       assertStatutoryDiscountType(endorsementQuoteResult, expectedPreFirmStatutoryDiscountType);
       assertStatutoryPremiumIncreaseCap(endorsementQuoteResult, expectedPreFirmStatutoryPremiumIncreaseCap);
       String quoteUuid1 = endorsementQuoteResult.get(TRANSACTION_ID).toString();
   }

   // NFIPEAS-17622
   @Test(groups = { "cap-api", "policy-quote", "policy-new-business" }, enabled = true)
   public void testRREndorsementQuotePreFirmDiscountNonPrimary() {
       String policyNumber = policyQuoteTestUtil.getTestPolicyNumber(TEST_NAIC_NUMBER);
       String expectedPreFirmStatutoryDiscountType = "NONE";
       String expectedPreFirmStatutoryPremiumIncreaseCap = "PREFIRM_DISCOUNT_NON_PRIMARY";

       // Get a policy quote, preFirmSubsidyEligible = false
       JSONObject quotePayload = policyQuoteTestUtil.getJSONObjectFromJSONFile(quoteFile);
       quotePayload.put(NAIC_NUM, TEST_NAIC_NUMBER);
       quotePayload.put(EFF_DATE, formattedQuoteEffDate);
       quotePayload.getJSONObject(PROP_CHARACTERISTICS_OBJ).put(PREFIRM_SUBSIDY_ELIGIBLE, true);
       quotePayload.getJSONObject(PROP_CHARACTERISTICS_OBJ).put(POST_FIRM_CONSTRUCTION, false);
       quotePayload.getJSONObject(PROP_CHARACTERISTICS_OBJ).put(PRIMARY_RESIDENCE,true);
       JSONObject quoteResult = policyQuoteTestUtil.apiCreatePolicyQuote(quotePayload, API_VERSION);
       Assert.assertNotNull(quoteResult, "Policy Quote Request result was null");
       String quoteUuid = quoteResult.get(TRANSACTION_ID).toString();

       // Get a rr2 policy
       JSONObject rrNewBusinessResult = createRR2Policy(newBusinessRequestJson, policyNumber, formattedTransDate, formattedPolicyEffDate, formattedExpDate, quoteUuid);
       createdTestPolicyIds.add(Long.valueOf(rrNewBusinessResult.getJSONObject(POLICY_OBJ).get(ID).toString()));

       // Get endorsement quote . preFirmSubsidyEligible = false , primaryResidence = false then
       // statutoryDiscountType  = "NONE" and statutoryPremiumIncreaseCap = PREFIRM_DISCOUNT_NON_PRIMARY"
       JSONObject endorsementQuotePayload = policyQuoteTestUtil.getJSONObjectFromJSONFile(endorsementQuoteJson);
       endorsementQuotePayload.put(ENDORSEMENT_EFF_DATE, formattedEndoQuoteEffDate);
       endorsementQuotePayload.put(NAIC_NUM, TEST_NAIC_NUMBER);
       endorsementQuotePayload.put(POLICY_NUM, policyNumber);
       endorsementQuotePayload.getJSONObject(PROP_CHARACTERISTICS_OBJ).put(PREFIRM_SUBSIDY_ELIGIBLE, false);
       endorsementQuotePayload.getJSONObject(PROP_CHARACTERISTICS_OBJ).put(POST_FIRM_CONSTRUCTION, false);
       endorsementQuotePayload.getJSONObject(PROP_CHARACTERISTICS_OBJ).put(PRIMARY_RESIDENCE,false);
       JSONObject endorsementQuoteResult = policyQuoteTestUtil.apiCreatePolicyEndorsementQuote(endorsementQuotePayload, API_VERSION);
       Assert.assertEquals(endorsementQuoteResult.get(CODE), HttpStatus.SC_CREATED, "Policy Quote Request result failed");
       assertStatutoryDiscountType(endorsementQuoteResult, expectedPreFirmStatutoryDiscountType);
       assertStatutoryPremiumIncreaseCap(endorsementQuoteResult, expectedPreFirmStatutoryPremiumIncreaseCap);
       String quoteUuid1 = endorsementQuoteResult.get(TRANSACTION_ID).toString();
   }

   // NFIPEAS-17622
   @Test(groups = { "cap-api", "policy-quote", "policy-new-business" }, enabled = true)
   public void testRREndorsementQuoteNoneDiscountFactor() {
       String policyNumber = policyQuoteTestUtil.getTestPolicyNumber(TEST_NAIC_NUMBER);
       String expectedNoneStatutoryDiscountType = "NONE";
       String expectedNoneStatutoryPremiumIncreaseCap = "STANDARD";

       // Get a policy quote
       JSONObject quotePayload = policyQuoteTestUtil.getJSONObjectFromJSONFile(quoteFile);
       quotePayload.put(NAIC_NUM, TEST_NAIC_NUMBER);
       quotePayload.put(EFF_DATE, formattedQuoteEffDate);
       JSONObject quoteResult = policyQuoteTestUtil.apiCreatePolicyQuote(quotePayload, API_VERSION);
       Assert.assertNotNull(quoteResult, "Policy Quote Request result was null");
       String quoteUuid = quoteResult.get(TRANSACTION_ID).toString();

       // Get a rr2 policy
       JSONObject rrNewBusinessResult = createRR2Policy(newBusinessRequestJson, policyNumber, formattedTransDate, formattedPolicyEffDate, formattedExpDate, quoteUuid);
       createdTestPolicyIds.add(Long.valueOf(rrNewBusinessResult.getJSONObject(POLICY_OBJ).get(ID).toString()));

       // Get endorsement quote
       JSONObject endorsementQuotePayload = policyQuoteTestUtil.getJSONObjectFromJSONFile(endorsementQuoteJson);
       endorsementQuotePayload.put(ENDORSEMENT_EFF_DATE, formattedEndoQuoteEffDate);
       endorsementQuotePayload.put(NAIC_NUM, TEST_NAIC_NUMBER);
       endorsementQuotePayload.put(POLICY_NUM, policyNumber);
       JSONObject endorsementQuoteResult = policyQuoteTestUtil.apiCreatePolicyEndorsementQuote(endorsementQuotePayload, API_VERSION);
       Assert.assertEquals(endorsementQuoteResult.get(CODE), HttpStatus.SC_CREATED, "Policy Quote Request result failed");
       assertStatutoryDiscountType(endorsementQuoteResult, expectedNoneStatutoryDiscountType);
       assertStatutoryPremiumIncreaseCap(endorsementQuoteResult, expectedNoneStatutoryPremiumIncreaseCap);
       String quoteUuid1 = endorsementQuoteResult.get(TRANSACTION_ID).toString();
   }


   // NFIPEAS-17623 OccupancyType is changing from RR_SINGLE_FAMILY(11) to RR_NON_RESIDENTIAL_MOBILE_MANUFACTURED(17)
   @Test(groups = { "cap-api", "policy-quote", "policy-new-business" }, enabled = true)
   public void testRREndorsementQuoteExemptFromStatutoryPremiumIncreaseCapOcType11To17() {
       String policyNumber = policyQuoteTestUtil.getTestPolicyNumber(TEST_NAIC_NUMBER);
       String expectedStatutoryDiscountType = "AR_FLOOD_ZONE_DISCOUNT_FACTOR";
       String expectedExemptStatutoryPremiumIncreaseCap = "NONE";

       // Get a policy quote
       JSONObject quotePayload = policyQuoteTestUtil.getJSONObjectFromJSONFile(quoteFile);
       quotePayload.put(NAIC_NUM, TEST_NAIC_NUMBER);
       quotePayload.put(EFF_DATE, formattedQuoteEffDate);
       JSONObject quoteResult = policyQuoteTestUtil.apiCreatePolicyQuote(quotePayload, API_VERSION);
       Assert.assertNotNull(quoteResult, "Policy Quote Request result was null");
       String quoteUuid = quoteResult.get(TRANSACTION_ID).toString();

       // Get a rr2 policy
       JSONObject rrNewBusinessResult = createRR2Policy(newBusinessRequestJson, policyNumber, formattedTransDate, formattedPolicyEffDate, formattedExpDate, quoteUuid);
       createdTestPolicyIds.add(Long.valueOf(rrNewBusinessResult.getJSONObject(POLICY_OBJ).get(ID).toString()));

       // Get endorsement quote
       JSONObject endorsementQuotePayload = policyQuoteTestUtil.getJSONObjectFromJSONFile(endorsementQuoteJson);
       endorsementQuotePayload.put(ENDORSEMENT_EFF_DATE, formattedEndoQuoteEffDate);
       endorsementQuotePayload.put(NAIC_NUM, TEST_NAIC_NUMBER);
       endorsementQuotePayload.put(POLICY_NUM, policyNumber);
       endorsementQuotePayload.getJSONObject(PROP_CHARACTERISTICS_OBJ).put(OCCUPANCY_TYPE, "17");
       endorsementQuotePayload.getJSONObject(PROP_CHARACTERISTICS_OBJ).getJSONObject(CONSTRUCTION_DETAILS_OBJ).put(BUILDING_DESCRIPTION, "32");
       endorsementQuotePayload.getJSONObject(PROP_CHARACTERISTICS_OBJ).getJSONObject(INDUSTRY_GEO_DETAILS_OBJ).put(FLOOD_ZONE, "AR");
       JSONObject endorsementQuoteResult = policyQuoteTestUtil.apiCreatePolicyEndorsementQuote(endorsementQuotePayload, API_VERSION);
       Assert.assertEquals(endorsementQuoteResult.get(CODE), HttpStatus.SC_CREATED, "Policy Quote Request result failed");
       assertStatutoryDiscountType(endorsementQuoteResult, expectedStatutoryDiscountType);
       assertStatutoryPremiumIncreaseCap(endorsementQuoteResult, expectedExemptStatutoryPremiumIncreaseCap);
       String quoteUuid1 = endorsementQuoteResult.get(TRANSACTION_ID).toString();
   }

   // NFIPEAS-17623 OccupancyType is changing from RR_SINGLE_FAMILY(11) to RR_NON_RESIDENTIAL_MOBILE_MANUFACTURED(17)
   @Test(groups = { "cap-api", "policy-quote", "policy-new-business" }, enabled = true)
   public void testRREndorsementQuoteExemptFromStatutoryPremiumIncreaseCapBuildingUnderConstruction() {
       String policyNumber = policyQuoteTestUtil.getTestPolicyNumber(TEST_NAIC_NUMBER);
       String expectedStatutoryDiscountType = "NEW_CUSTOMER_PREFIRM_DISCOUNT_FACTOR";
       String expectedExemptStatutoryPremiumIncreaseCap = "NONE";

       // Get a policy quote
       JSONObject quotePayload = policyQuoteTestUtil.getJSONObjectFromJSONFile(quoteFile);
       quotePayload.put(NAIC_NUM, TEST_NAIC_NUMBER);
       quotePayload.put(EFF_DATE, formattedQuoteEffDate);
       quotePayload.getJSONObject(PROP_CHARACTERISTICS_OBJ).put(PREFIRM_SUBSIDY_ELIGIBLE, true);
       quotePayload.getJSONObject(PROP_CHARACTERISTICS_OBJ).put(POST_FIRM_CONSTRUCTION, false);
       quotePayload.getJSONObject(PROP_CHARACTERISTICS_OBJ).put(PRIMARY_RESIDENCE, true);
       quotePayload.getJSONObject(PROP_CHARACTERISTICS_OBJ).getJSONObject(CONSTRUCTION_DETAILS_OBJ).put(BUILDING_UNDER_CONSTRUCTION, true);
       JSONObject quoteResult = policyQuoteTestUtil.apiCreatePolicyQuote(quotePayload, API_VERSION);
       Assert.assertNotNull(quoteResult, "Policy Quote Request result was null");
       String quoteUuid = quoteResult.get(TRANSACTION_ID).toString();

       // Get a rr2 policy
       JSONObject rrNewBusinessResult = createRR2Policy(newBusinessRequestJson, policyNumber, formattedTransDate, formattedPolicyEffDate, formattedExpDate, quoteUuid);
       createdTestPolicyIds.add(Long.valueOf(rrNewBusinessResult.getJSONObject(POLICY_OBJ).get(ID).toString()));

       // Get endorsement quote
       JSONObject endorsementQuotePayload = policyQuoteTestUtil.getJSONObjectFromJSONFile(endorsementQuoteJson);
       endorsementQuotePayload.put(ENDORSEMENT_EFF_DATE, formattedEndoQuoteEffDate);
       endorsementQuotePayload.put(NAIC_NUM, TEST_NAIC_NUMBER);
       endorsementQuotePayload.put(POLICY_NUM, policyNumber);
       endorsementQuotePayload.getJSONObject(PROP_CHARACTERISTICS_OBJ).put(PREFIRM_SUBSIDY_ELIGIBLE, true);
       endorsementQuotePayload.getJSONObject(PROP_CHARACTERISTICS_OBJ).put(POST_FIRM_CONSTRUCTION, false);
       endorsementQuotePayload.getJSONObject(PROP_CHARACTERISTICS_OBJ).put(PRIMARY_RESIDENCE, true);
       endorsementQuotePayload.getJSONObject(PROP_CHARACTERISTICS_OBJ).getJSONObject(CONSTRUCTION_DETAILS_OBJ).put(BUILDING_UNDER_CONSTRUCTION, false);
       JSONObject endorsementQuoteResult = policyQuoteTestUtil.apiCreatePolicyEndorsementQuote(endorsementQuotePayload, API_VERSION);
       Assert.assertEquals(endorsementQuoteResult.get(CODE), HttpStatus.SC_CREATED, "Policy Quote Request result failed");
       assertStatutoryDiscountType(endorsementQuoteResult, expectedStatutoryDiscountType);
       assertStatutoryPremiumIncreaseCap(endorsementQuoteResult, expectedExemptStatutoryPremiumIncreaseCap);
       String quoteUuid1 = endorsementQuoteResult.get(TRANSACTION_ID).toString();
   }

   // NFIPEAS-17623 OccupancyType is changing from RR_SINGLE_FAMILY(11) to RR_NON_RESIDENTIAL_MOBILE_MANUFACTURED(17)
   @Test(groups = { "cap-api", "policy-quote", "policy-new-business" }, enabled = true)
   public void testRREndorsementQuoteExemptFromStatutoryPremiumIncreaseCapSubstantialImprovementDate() {
       String policyNumber = policyQuoteTestUtil.getTestPolicyNumber(TEST_NAIC_NUMBER);
       LocalDate substantialImprovementDate = LocalDate.of(2016,1,11);
       String formattedSubstantialImprovementDate = substantialImprovementDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
       String expectedStatutoryDiscountType = "NEW_CUSTOMER_PREFIRM_DISCOUNT_FACTOR";
       String expectedExemptStatutoryPremiumIncreaseCap = "NONE";

       // Get a policy quote
       JSONObject quotePayload = policyQuoteTestUtil.getJSONObjectFromJSONFile(quoteFile);
       quotePayload.put(NAIC_NUM, TEST_NAIC_NUMBER);
       quotePayload.put(EFF_DATE, formattedQuoteEffDate);
       quotePayload.getJSONObject(PROP_CHARACTERISTICS_OBJ).put(PREFIRM_SUBSIDY_ELIGIBLE, true);
       quotePayload.getJSONObject(PROP_CHARACTERISTICS_OBJ).put(POST_FIRM_CONSTRUCTION, false);
       quotePayload.getJSONObject(PROP_CHARACTERISTICS_OBJ).put(PRIMARY_RESIDENCE, true);
       JSONObject quoteResult = policyQuoteTestUtil.apiCreatePolicyQuote(quotePayload, API_VERSION);
       Assert.assertNotNull(quoteResult, "Policy Quote Request result was null");
       String quoteUuid = quoteResult.get(TRANSACTION_ID).toString();

       // Get a rr2 policy
       JSONObject rrNewBusinessResult = createRR2Policy(newBusinessRequestJson, policyNumber, formattedTransDate, formattedPolicyEffDate, formattedExpDate, quoteUuid);
       createdTestPolicyIds.add(Long.valueOf(rrNewBusinessResult.getJSONObject(POLICY_OBJ).get(ID).toString()));

       // Get endorsement quote
       JSONObject endorsementQuotePayload = policyQuoteTestUtil.getJSONObjectFromJSONFile(endorsementQuoteJson);
       endorsementQuotePayload.put(ENDORSEMENT_EFF_DATE, formattedEndoQuoteEffDate);
       endorsementQuotePayload.put(NAIC_NUM, TEST_NAIC_NUMBER);
       endorsementQuotePayload.put(POLICY_NUM, policyNumber);
       endorsementQuotePayload.getJSONObject(PROP_CHARACTERISTICS_OBJ).put(PREFIRM_SUBSIDY_ELIGIBLE, true);
       endorsementQuotePayload.getJSONObject(PROP_CHARACTERISTICS_OBJ).put(POST_FIRM_CONSTRUCTION, false);
       endorsementQuotePayload.getJSONObject(PROP_CHARACTERISTICS_OBJ).put(PRIMARY_RESIDENCE, true);
       endorsementQuotePayload.getJSONObject(PROP_CHARACTERISTICS_OBJ).getJSONObject(CONSTRUCTION_DETAILS_OBJ).put(SUBSTANTIAL_IMPROVEMENT_DATE, formattedSubstantialImprovementDate);
       JSONObject endorsementQuoteResult = policyQuoteTestUtil.apiCreatePolicyEndorsementQuote(endorsementQuotePayload, API_VERSION);
       Assert.assertEquals(endorsementQuoteResult.get(CODE), HttpStatus.SC_CREATED, "Policy Quote Request result failed");
       assertStatutoryDiscountType(endorsementQuoteResult, expectedStatutoryDiscountType);
       assertStatutoryPremiumIncreaseCap(endorsementQuoteResult, expectedExemptStatutoryPremiumIncreaseCap);
       String quoteUuid1 = endorsementQuoteResult.get(TRANSACTION_ID).toString();
   }

   public void assertStatutoryDiscountType(JSONObject quoteResult, String expectedStatutoryDiscountType) {
       String statutoryDiscountType = quoteResult.getJSONObject("quoteFactors").get("statutoryDiscountType").toString();
       Assert.assertTrue(statutoryDiscountType.equalsIgnoreCase(expectedStatutoryDiscountType), "Expected expectedStatutoryDiscountType does not match actual value ") ;
   }

   public void assertStatutoryPremiumIncreaseCap(JSONObject quoteResult, String expectedStatutoryDiscountType) {
       String statutoryPremiumIncreaseCap = quoteResult.getJSONObject("quoteFactors").get("statutoryPremiumIncreaseCap").toString();
       Assert.assertTrue(statutoryPremiumIncreaseCap.equalsIgnoreCase(expectedStatutoryDiscountType), "Expected statutoryPremiumIncreaseCap does not match actual value ") ;
   }

   // this assert will need to be update to match the endorsement quote whe that is constructed
   public void assertRRNewBusinessPolicyResult(JSONObject newBusinessResult, Map<String, String> expectedValues){
       String actualFirstName = "";
       String actualLastName = "";
       String actualPropertyAddress = "";
       String actualPolicyNumber = "";
       String actualPremium = "";
       String actualEffectiveDate = "";
       String actualExpirationDate = "";
       String actualCondoAssociation = "";
       String actualMobileHomeId = "";
       String actualNumDetachedStructures = "";
       String actualNumElevators = "";
       String actualStateOwned = "";
       String actualNonProfit = "";
       String actualSmallBusiness = "";
       try {
           JSONObject policy = newBusinessResult.getJSONObject(POLICY_OBJ);
           actualFirstName = policy.get(FIRST_NAME).toString();
           actualLastName = policy.get(LAST_NAME).toString();
           JSONObject policyAddress = (JSONObject) policy.getJSONArray(ADDRESSES_ARRY).get(0);
           actualPropertyAddress = policyAddress.get(ADDR_LINE_1).toString();
           actualPolicyNumber = policy.get(POLICY_NUM).toString();
           actualNonProfit = policy.get(NON_PROFIT).toString();
           actualSmallBusiness = policy.get(SMALL_BUSINESS).toString();
           JSONObject rrPolicyPeriod = policy.getJSONObject(RR_POLICY_PERIOD_OBJ);
           actualPremium = ((JSONObject)rrPolicyPeriod.getJSONArray(RR_POLICY_ACCOUNTING_ARRY).get(0)).get(PREMIUM_OBJ).toString();
           actualEffectiveDate = rrPolicyPeriod.get(EFF_DATE).toString();
           actualExpirationDate = rrPolicyPeriod.get(EXP_DATE).toString();
           actualCondoAssociation = policy.get(CONDO_ASSOCIATION).toString();
           JSONObject rrPremises = policy.getJSONObject(RR_PREMISES_OBJ);
           actualMobileHomeId = rrPremises.get(MOBILE_HOME_ID).toString();
           actualNumDetachedStructures = rrPremises.get(NUM_DETACHED_STRUCTURES).toString();
           actualNumElevators = rrPremises.get(NUM_ELEVATORS).toString();
           actualStateOwned = rrPremises.get(STATE_OWNED).toString();
       }catch(org.json.JSONException je){
           logger.error("JSON PARSE EXCEPTION: assertRRNewBusinessPolicyResult");
           Assert.fail(je.getMessage());
       }
       SoftAssert softAssert = new SoftAssert();
       softAssert.assertEquals(actualFirstName, expectedValues.get(FIRST_NAME), FIRST_NAME +  " incorrect");
       softAssert.assertEquals(actualLastName, expectedValues.get(LAST_NAME), LAST_NAME + " incorrect");
       softAssert.assertEquals(actualPropertyAddress, expectedValues.get(ADDR_LINE_1), ADDR_LINE_1 + " incorrect");
       softAssert.assertEquals(actualPolicyNumber, expectedValues.get(POLICY_NUM), POLICY_NUM + " incorrect");
       softAssert.assertEquals(actualPremium, expectedValues.get(PREMIUM_OBJ), PREMIUM_OBJ + " incorrect");
       softAssert.assertEquals(actualEffectiveDate, expectedValues.get(EFF_DATE), EFF_DATE + " incorrect");
       softAssert.assertEquals(actualExpirationDate, expectedValues.get(EXP_DATE),  EXP_DATE + " incorrect");
       softAssert.assertEquals(actualCondoAssociation, expectedValues.get(CONDO_ASSOCIATION),  CONDO_ASSOCIATION + " incorrect");
       softAssert.assertEquals(actualMobileHomeId, expectedValues.get(MOBILE_HOME_ID),  MOBILE_HOME_ID + " incorrect");
       softAssert.assertEquals(actualNumDetachedStructures, expectedValues.get(NUM_DETACHED_STRUCTURES),  NUM_DETACHED_STRUCTURES+ " incorrect");
       softAssert.assertEquals(actualNumElevators, expectedValues.get(NUM_ELEVATORS),  NUM_ELEVATORS + " incorrect");
       softAssert.assertEquals(actualStateOwned, expectedValues.get(STATE_OWNED),  STATE_OWNED + " incorrect");
       softAssert.assertEquals(actualSmallBusiness, expectedValues.get(SMALL_BUSINESS),  SMALL_BUSINESS + " incorrect");
       softAssert.assertEquals(actualNonProfit, expectedValues.get(NON_PROFIT),  NON_PROFIT + " incorrect");
       softAssert.assertAll();
   }

   public JSONObject createPolicyUsingQuote(String naicNumber, String quoteUuid, LocalDate tranDate )
   {
       String newBusinessRequestJson = "/PolicyQuoteFiles/SuccessFiles/rrNewBusinessPolicyRequest.json";
       String policyNumber = policyQuoteTestUtil.getTestPolicyNumber(naicNumber);
       LocalDate transactionDate = tranDate;
       LocalDate effectiveDate = LocalDate.of(transactionDate.getYear(), transactionDate.getMonth().plus(1), transactionDate.getDayOfMonth());;
       LocalDate expirationDate = effectiveDate.plusYears(1);
       String formattedTransDate = transactionDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
       String formattedEffDate = effectiveDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
       String formattedExpDate = expirationDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
       String expectedQuoteTransactionType = "NEW_BUSINESS_QUOTE";

       JSONObject newBusinessPayload = policyQuoteTestUtil.getJSONObjectFromJSONFile(newBusinessRequestJson);
       newBusinessPayload.put(QUOTE_UUID, quoteUuid);
       newBusinessPayload.put(POLICY_NUM, policyNumber);
       newBusinessPayload.put(NAIC_NUM, TEST_NAIC_NUMBER);
       newBusinessPayload.put(TRANSACTION_DATE,formattedTransDate);
       JSONObject policyPeriod = newBusinessPayload.getJSONObject(RR_POLICY_PERIOD_OBJ);
       policyPeriod.put(EFF_DATE, formattedEffDate);
       policyPeriod.put(EXP_DATE, formattedExpDate);
       newBusinessPayload.put(RR_POLICY_PERIOD_OBJ, policyPeriod);
       JSONObject rrPremises = newBusinessPayload.getJSONObject(RR_PREMISES_OBJ);
       rrPremises.put(NUM_DETACHED_STRUCTURES, "1");
       rrPremises.put(NUM_ELEVATORS, "1");
       newBusinessPayload.put(RR_PREMISES_OBJ, rrPremises);
       return newBusinessPayload;
   }

   public JSONObject createPolicyQuote(String naicNumber, LocalDate tranDate, String quoteJson )
   {
       LocalDate transactionDate = tranDate;
       LocalDate effectiveDate = LocalDate.of(transactionDate.getYear(), transactionDate.getMonth().plus(1), transactionDate.getDayOfMonth());;
       LocalDate expirationDate = effectiveDate.plusYears(1);
       String formattedTransDate = transactionDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
       String formattedEffDate = effectiveDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
       String formattedExpDate = expirationDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
       String expectedQuoteTransactionType = "NEW_BUSINESS_QUOTE";

       JSONObject quotePayload = policyQuoteTestUtil.getJSONObjectFromJSONFile(quoteJson);
       quotePayload.put(EFF_DATE, formattedEffDate);
       quotePayload.put(NAIC_NUM, naicNumber);
       return quotePayload;
   }

   private void assertRuleFailureUUID(JSONObject quoteResult, String uuid, Boolean failurePresent){
       List ruleFailureUuids = new ArrayList();
       JSONArray ruleFailures = quoteResult.getJSONArray(RULE_FAILURES_OBJ);
       if(failurePresent) {
           Assert.assertFalse(ruleFailures.isEmpty(), "No Rule Failures were found");
       }
       for(int i = 0; i < ruleFailures.length(); i++ ){
           JSONObject ruleFailure = (JSONObject) ruleFailures.get(i);
           ruleFailureUuids.add(((JSONObject)ruleFailure.get(RULE_OBJ)).get(UUID).toString());
       }
       if(failurePresent) {
           Assert.assertTrue(ruleFailureUuids.contains(uuid), "Rule failure with uuid: " + uuid + " was not found");
       }else{
           Assert.assertFalse(ruleFailureUuids.contains(uuid), "Rule failure with uuid: " + uuid + " was found");
       }
   }
   // Create rr2 policy method
   private JSONObject createRR2Policy(String rr2PolicyJsonPath, String policyNumber, String formattedTransDate,String formattedEffDate, String formattedExpDate, String quoteUuid ) {
       JSONObject newBusinessPayload = policyQuoteTestUtil.getJSONObjectFromJSONFile(rr2PolicyJsonPath);
       newBusinessPayload.put(QUOTE_UUID, quoteUuid);
       newBusinessPayload.put(POLICY_NUM, policyNumber);
       newBusinessPayload.put(NAIC_NUM, TEST_NAIC_NUMBER);
       JSONObject policyPeriod = newBusinessPayload.getJSONObject(RR_POLICY_PERIOD_OBJ);
       policyPeriod.put(EFF_DATE, formattedEffDate);
       policyPeriod.put(TRANSACTION_DATE, formattedTransDate);
       policyPeriod.put(EXP_DATE, formattedExpDate);
       newBusinessPayload.put(RR_POLICY_PERIOD_OBJ, policyPeriod);
       JSONObject rrNewBusinessResult = policyQuoteTestUtil.apiCreateNewBusinessPolicyRequest(newBusinessPayload, API_VERSION);
       Assert.assertEquals(rrNewBusinessResult.get(CODE), HttpStatus.SC_CREATED, "RR2 New Business Policy was not created");
       createdTestPolicyIds.add(Long.valueOf(rrNewBusinessResult.getJSONObject(POLICY_OBJ).get(ID).toString()));
       return rrNewBusinessResult;
   }
}


