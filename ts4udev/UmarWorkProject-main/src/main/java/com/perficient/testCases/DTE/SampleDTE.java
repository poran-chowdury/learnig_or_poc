package com.perficient.testCases.DTE;

import com.perficient.testcasebase.dte.DTEHomepage;
import com.perficient.util.SystemUtil;
// import com.perficient.util.CSVUtil;
import com.perficient.util.TestCaseBase;

import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

//import com.perficient.bing.basepages.BingHomePage;

public class SampleDTE extends TestCaseBase {
//	 private static final String FILE_PATH = "testdata/newuseraddress.csv";

	@Test(priority = 1, groups = { "firefox", "IEWin32", "ChromeWin32", "browserPercentageSpecified" })
	public void testHomePageLoaded() throws Exception {
		DTEHomepage dteHomepage = new DTEHomepage(pageManager, excelReader);
		dteHomepage.open();
		customAssertion.assertTrue(pageManager.getTitle().contains(DTEHomepage.TITLE));
	}
	
	@Test(priority = 10, groups = { "firefox", "IEWin32", "ChromeWin32", "browserPercentageSpecified" })
	public void testGetStartedLink() throws Exception {
		 DTEHomepage dteHomepage = new DTEHomepage(pageManager, excelReader);
		dteHomepage.getStartedLink();
		dteHomepage.sleep();
		customAssertion.assertTrue(pageManager.getText(dteHomepage.getModalTitle).contains(DTEHomepage.MODALTITLE));	
	}
	
	@Test(priority = 20, groups = { "firefox", "IEWin32", "ChromeWin32", "browserPercentageSpecified" })
	public void testgetStartServiceButton() throws Exception {
		 DTEHomepage dteHomepage = new DTEHomepage(pageManager, excelReader);
		dteHomepage.getStartServiceButton();
		dteHomepage.sleep();
		customAssertion.assertTrue(pageManager.getText(dteHomepage.getSignInModal).contains(DTEHomepage.SIGNINTITLE));
		dteHomepage.getCountinueButton();
		dteHomepage.sleep();
	}
	
	 @Test(priority = 30)
	    public void testVerifyAddress() throws InterruptedException{
			DTEHomepage dteHomepage = new DTEHomepage(pageManager, excelReader);
			 dteHomepage.open();
				dteHomepage.getStartedLink();
				dteHomepage.sleep();
				dteHomepage.getStartServiceButton();
				dteHomepage.sleep();
				dteHomepage.getCountinueButton();
				dteHomepage.sleep();
			 //dteHomepage.enterAddress("12011 Ashton Ave", "Detroit", "48228");
			dteHomepage.closePopup();
			 Properties PROPERTIES_RESOURCES = SystemUtil.loadPropertiesResources("/testdata_dte.properties");
				String ADDRESS = PROPERTIES_RESOURCES.getProperty("dte.address");
				String CITY = PROPERTIES_RESOURCES.getProperty("dte.city");
				String ZIP = PROPERTIES_RESOURCES.getProperty("dte.zip");
				System.out.println("ADDRESS , CITY, ZIP" + ADDRESS + CITY + ZIP);
				dteHomepage.enterAddress(ADDRESS,CITY,ZIP);
			 dteHomepage.clickVerifyAddressButon();
			 customAssertion.assertTrue(pageManager.getTextElement(dteHomepage.selectVerifiedAddress).contains(DTEHomepage.SELECTVERIFIEDADDRESS));
			 dteHomepage.sleep();
			 dteHomepage.getAddressVerificationContinueButton();
			 dteHomepage.sleep();
			 dteHomepage.getServiceAddressNextStep();
			dteHomepage.sleep();
			customAssertion.assertTrue(pageManager.getText(dteHomepage.getCustomerType).contains(DTEHomepage.CUSTOMERTYPE));
			dteHomepage. getContinueCustomerType();
			dteHomepage.sleep();
			 
	 }
	 
 
	 
	 
	 @Test(priority = 60)
	    public void testCustomerInfoVerification() throws InterruptedException{
			DTEHomepage dteHomepage = new DTEHomepage(pageManager, excelReader);
			 dteHomepage.enterAccountInfo("JOSEPH", "YOURCHISIN", "YOURCHISIN@test.com","YOURCHISIN@test.com","123.456.8900","Test1234","Test1234");
			 dteHomepage.sleep();
			 dteHomepage.enterMovingAddress("198 PINE ST","NORWELL","02061-2617"); 
			 dteHomepage.sleep();
			 dteHomepage.enterIdenityInfo("01311937","666074947");
			 dteHomepage.sleep();
	 }
	 
	 
	 
	 
//	 @DataProvider
//	    public Iterator<Object[]> getData() throws IOException {
//	        return CSVUtil.parseCsvData(FILE_PATH);
//	 }
}