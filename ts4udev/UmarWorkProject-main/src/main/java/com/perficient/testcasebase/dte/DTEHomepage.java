package com.perficient.testcasebase.dte;

import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

// import com.perficient.util.CSVUtil;
import com.perficient.util.DatabaseUtil;
import com.perficient.util.ExcelReader;
import com.perficient.util.FTPTransfer;
import com.perficient.util.PageManager;
import com.perficient.util.PageObject;
import com.perficient.util.SystemUtil;

public class DTEHomepage extends PageObject {
	public static String TITLE = "Start or Stop Service";
	public static String MODALTITLE = "Start Service";
	public static String SIGNINTITLE="Welcome! To sign up for service online, you'll need to use a DTE Energy account"; 
	public static String CUSTOMERTYPE="Customer Type";
	public static String STOPMODALTITLE = "Stop Service";
	public static String REMINDERMODALTITLE = "Service Request Reminder";
	public static String STREETADDRESSERROR = "Please correct street address";
	public static String ZIPCODEERROR ="Please correct ZIP code";
	public static String SELECTVERIFIEDADDRESS="Select a verified address:";
	 
	@FindBy(xpath = "//*[@id=\"app\"]/div[2]/div[2]/a[1]/div/div[4]/div/span[1]")
	 WebElement getStartedLink;
	
	@FindBy(id = "modalLabel")
	public WebElement getModalTitle;
	
	@FindBy(xpath = "//*[@id=\"mimo-instructions-modal\"]/div/div/div[2]/div/div[2]/div/a")
	 WebElement getStartServiceButton;

	@FindBy(xpath = "//*[@id=\"login-modal\"]/div/div/div/div[1]/h4")
	public WebElement getSignInTitle;
	
	@FindBy(xpath = "//*[@id=\"login-modal\"]/div/div/div/div[2]/div/div[2]/div[3]/div/button")
	public WebElement getCountinueButton;

	@FindBy(id = "streetAddress")
	public WebElement streetAddressField;
	
	@FindBy(id="unitType")
	public WebElement unitType;
	
	@FindBy(id="unitNumber")
	public WebElement unitNumber;
	
	@FindBy(id = "city")
	public WebElement cityField;
	
	@FindBy(id= "zipcode")
	public WebElement zipField;
	
	@FindBy(id= "state")
	public WebElement stateDropDown;
	
	@FindBy(id= "search-address")
	public WebElement getVerifyAddressButton;
	
	@FindBy(xpath= "//*[@id=\"search-address\"]")
	public WebElement getVerifyBillingButton;

	
	@FindBy(xpath= "//*[@id=\"section-1\"]/div/div/div[4]/div/button")
	public WebElement getAddressVerificationContinueButton;
	
	
	@FindBy(xpath= "//*[@id=\"app\"]/div[2]/div[3]/div/div/button")
	public WebElement getServiceAddressNextStep;
	
	
	@FindBy(id="accordian-0")
	public WebElement getCustomerType;
	
	
	@FindBy(xpath="//*[@id=\"login-modal\"]/div/div/div/div[1]/h4")
	public WebElement getSignInModal;
	

	@FindBy(xpath="//*[@id=\"section-0\"]/form/div[2]/div/button")
	public WebElement getContinueCustomerType;
	
	//@FindBy(xpath="//*[@id=\"section-1\"]/form/div[1]/fieldset/div[1]/label[1]/input")
	@FindBy(xpath="//body/main[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/form[1]/div[1]/fieldset[1]/div[1]/label[1]/input[1]")
	public WebElement getFirstNameInput;
	
	@FindBy(xpath="//*[@id=\"section-1\"]/form/div[1]/fieldset/div[1]/label[2]/input")
	public WebElement getLastNameInput;
	
	@FindBy(xpath="//*[@id=\"section-1\"]/form/div[1]/fieldset/div[2]/label[1]/input")
	public WebElement getEmailInput;
	
	@FindBy(xpath="//*[@id=\"section-1\"]/form/div[1]/fieldset/div[2]/label[2]/input")
	public WebElement getConfirmEmailInput;
	
	@FindBy(xpath="//*[@id=\"section-1\"]/form/div[1]/fieldset/div[3]/label/input")
	public WebElement getPhoneInput;
	
	@FindBy(xpath="//*[@id=\"password\"]")
	public WebElement getPasswordInput;
	
	@FindBy(xpath="//*[@id=\"password-confirm\"]")
	public WebElement getConfirmPasswordInput;
	
	
	@FindBy(xpath="//*[@id=\"section-1\"]/form/div[2]/div/button")
	public WebElement getAccountInfoContinueButton;
	
	@FindBy(id="verifyAddr")
	public WebElement getVerifyMovingAddressButton;
	
	@FindBy(xpath = "//*[@id=\"pidSection\"]/form/div/fieldset/div[2]/div/input")
	public WebElement movingAddressInput;
	
	@FindBy(xpath = "//*[@id=\"pidSection\"]/form/div/fieldset/div[5]/div/input")
	public WebElement movingCityInput;
	
	@FindBy(xpath= "//*[@id=\"pidSection\"]/form/div/fieldset/div[6]/div/input")
	public WebElement movingZipInput;
	
	@FindBy(id= "dateOfBirth")
	public WebElement dobInput;
	
	@FindBy(id= "identificationValue")
	public WebElement ssnInput;
	
	
	@FindBy(xpath= "//*[@id=\"pidSection\"]/form/div[4]/div/button")
	public WebElement getIdentityContinueButton;
	
	
	/////////////////////////////////////Existing User ////////////////////////////////////////////////////////////
	@FindBy(id= "userEmail")
	public WebElement existingEmailInput;
	
	@FindBy(id= "userPassword")
	public WebElement existingPasswordInput;
	
	@FindBy(xpath= "//*[@id=\"sign-in-form\"]/div[6]/div/button")
	public WebElement getSignInButton;
	
	
	@FindBy(xpath= "//*[@id=\"app\"]/div[2]/div[2]/a[2]/div/div[4]/div/span[1]")
	public WebElement getStopServiceLink;
	
	@FindBy(xpath = "//*[@id=\"modalLabel\"]")
	public WebElement getStopModalTitle;
	
	@FindBy(xpath= "//*[@id=\"mimo-instructions-modal\"]/div/div/div[2]/div/div[2]/div/a")
	public WebElement getStopServiceModalButton;
	
	@FindBy(xpath= "//*[@id=\"current-service-address\"]/div/div[1]/div/label/span")
	public WebElement getAddressRadioButton;
	
	@FindBy(xpath= "//*[@id=\"section-0\"]/div[1]/div[3]/div/button")
	public WebElement getAddressCountinueButton;
	
	@FindBy(xpath= "//*[@id=\"app\"]/div[2]/div[3]/div/div/button")
	public WebElement getNextStepStopServiceAddressButton;
	
	
	@FindBy(id = "identificationValue")
	public WebElement enterExistingSsnInput;
	
	@FindBy(id = "identificationType")
	public WebElement getDropdownOptionSsn;
	
	@FindBy(xpath = "//*[@id=\"app\"]/div[2]/div[3]/div/div/button")
	public WebElement getNextStepStopServiceSsnButton;
	
	
	@FindBy(id = "dateInput")
	public WebElement getServiceStopDate;
	
	
	@FindBy(xpath="//*[@id=\"premise-info-form\"]/div/div[4]/input")
	public WebElement getServiceStopPhoneInput;
	
	
	@FindBy(xpath="//*[@id=\"mimo-collapse\"]/div[2]/div/div/button")
	public WebElement getServiceStopContinueButton;
	
	
	@FindBy(id = "advisementRead")
	public WebElement getReminderButton;
	
	@FindBy(id = "advisementsModalLabel")
	public WebElement getReminderModal;
	
	
	
	@FindBy(xpath = "//*[@id=\"app\"]/div[2]/div[3]/div/div/button")
	public WebElement getBillingNextStepButton;
	
	
	@FindBy(xpath = "//span[contains(text(),'Please correct street address')]")
	public WebElement streetAddressError;
	
	@FindBy(xpath = "//span[contains(text(),'Please correct ZIP code')]")
	public WebElement zipCodeError;
	
	@FindBy(xpath= "//label[contains(text(),'Select a verified address:')]")
	public WebElement selectVerifiedAddress;
	
	@FindBy(xpath="//*[@id='fsrInvite']")
	public WebElement popUp;
	
	@FindBy(xpath ="//*[@id='fsrInvite']/section[3]/button[2]")
	public WebElement closeButton;
	
	public DTEHomepage(PageManager pm, ExcelReader excelReader) {
		super(pm, excelReader);
	}
	
	public void open() {
		Properties PROPERTIES_RESOURCES = SystemUtil.loadPropertiesResources("/testdata_dte.properties");
		String URL = PROPERTIES_RESOURCES.getProperty("dte.url");
		pageManager.navigate(URL);
	}
	
	public void setStreetAddress(String streetAddress) {
		streetAddressField.sendKeys(streetAddress);
	}
	
	public void setCity(String city) {
		cityField.sendKeys(city);
	}
	
	public void setZip(String zip) {
		zipField.sendKeys(zip);
	}

	public void setIdenityDOB(String dob) {
		dobInput.sendKeys(dob);
	}
	public void setIdenitySSN(String ssn) {
		ssnInput.sendKeys(ssn);
	}
	public void setFirstName(String firstName) {
		getFirstNameInput.sendKeys(firstName);
	}
	public void setLastName(String lastName) {
		getLastNameInput.sendKeys(lastName);
	}
	
	public void setEmail(String email) {
		getEmailInput.sendKeys(email);
	}
	public void setConfirmEmail(String confirmEmail) {
		getConfirmEmailInput.sendKeys(confirmEmail);
	}
	
	public void setPhone(String phone) {
		getPhoneInput.sendKeys(phone);
	}
	public void setPassword(String password) {
		getPasswordInput.sendKeys(password);
	}
	public void setConfirmPassword(String confirmPassword) {
		getConfirmPasswordInput.sendKeys(confirmPassword);
	}
	
	public void getStartedLink() {
		pageManager.untilAvalible(getStartedLink);
		pageManager.click(getStartedLink);	
	}
	
	public void getStartServiceButton() {
		pageManager.untilAvalible(getStartServiceButton);
		pageManager.click(getStartServiceButton);	
	}
	
	public void getCountinueButton() {
		pageManager.untilAvalible(getCountinueButton);
		pageManager.click(getCountinueButton);	
	}
	
	public void enterAddress(String streetAddress, String city, String zip) {
		setStreetAddress(streetAddress);
		setCity(city);
		setZip(zip);			
	}	
	
	public void enterFullAddress(String streetAddress, String UnitType,   String city, String zip) {
		setStreetAddress(streetAddress);
		setCity(city);
		setZip(zip);			
	}	
	public void clickVerifyAddressButon() {
		getVerifyAddressButton.click();
	}
	
	public void setMovingStreetAddress(String streetAddress) {
		movingAddressInput.sendKeys(streetAddress);
	}
	
	public void setMovingCity(String city) {
		movingCityInput.sendKeys(city);
	}
	
	public void setMovingZip(String zip) {
		movingZipInput.sendKeys(zip);
	}

	public void enterAccountInfo(String firstName, String lastName, String email,String confirmEmail,String phone,String password,String confirmPassword) {
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
		setConfirmEmail(confirmEmail);
		setPassword(password);
		setConfirmPassword(confirmPassword);
		setPhone(phone);
		getAccountInfoContinueButton.click();	
	}
	
	public void enterMovingAddress(String streetAddress, String city, String zip) {
		setMovingStreetAddress(streetAddress);
		setMovingCity(city);
		setMovingZip(zip);
		getVerifyMovingAddressButton.click();	
		
	}
	
	public void enterIdenityInfo(String dob, String ssn) throws InterruptedException {
		pageManager.sleep(10000);
		setIdenityDOB(dob);
		setIdenitySSN(ssn);
//		getcaptcha.click();
//		pageManager.sleep(20000);
		pageManager.waitAndClick(getIdentityContinueButton, 60000);
		pageManager.sleep(10000);
//		getIdentityContinueButton.click();
	}
	////////////////////////////Existing User//////////////////////////////////////
	
	
	public void setExistingEmail(String email) {
		existingEmailInput.sendKeys(email);
	}
	public void setExistingPassword(String password) {
		existingPasswordInput.sendKeys(password);
	}
	public void enterExistingUser(String email, String password) {
		setExistingEmail(email);
		setExistingPassword(password);
		getSignInButton.click();	
	}
	
	
	public void getStopServiceLink() {
		pageManager.untilAvalible(getStopServiceLink);
		pageManager.click(getStopServiceLink);	
	}
	public void getStopServiceModalButton() {
		pageManager.untilAvalible(getStopServiceModalButton);
		pageManager.click(getStopServiceModalButton);	
	}
	
	
	public void getAddressVerificationContinueButton() {
		pageManager.untilAvalible(getAddressVerificationContinueButton);	
		getAddressVerificationContinueButton.click();	
	}
	
	public void getServiceAddressNextStep() {
		pageManager.untilAvalible(getServiceAddressNextStep);	
		getServiceAddressNextStep.click();	
	}
	
	public void getContinueCustomerType() {
		pageManager.untilAvalible(getContinueCustomerType);	
		getContinueCustomerType.click();	
	}
	
	public void getAddressRadioButton() {
		pageManager.untilAvalible(getAddressRadioButton);	
		getAddressRadioButton.click();	
	}
	public void getAddressCountinueButton() {
		pageManager.untilAvalible(getAddressCountinueButton);	
		getAddressCountinueButton.click();	
	}
	
	 public void  getNextStepStopServiceAddressButton() {
		pageManager.untilAvalible(getNextStepStopServiceAddressButton);	
		getNextStepStopServiceAddressButton.click();	
	}
	 
	 public void  enterServiceStopDate(String date) {
			pageManager.untilAvalible(getServiceStopDate);	
			setServiceStopDate(date);
	}
	 public void setServiceStopDate(String date) {
		 getServiceStopDate.sendKeys(date);
	}
	 
	public void enterExistingSsn(String ssn) throws InterruptedException {
		pageManager.dropDownHandlingByIndex(getDropdownOptionSsn,1);  
	    setEnterExistingSsn(ssn);
        pageManager.sleep(5000);
	    getNextStepStopServiceSsnButton.click();	
	} 
	
	public void enterExistingState() throws InterruptedException {
		pageManager.dropDownHandlingByIndex(stateDropDown,27);  
        pageManager.sleep(5000);
//        getVerifyAddressButton.click();		
	} 
	
	public void setEnterExistingSsn(String ssn) {
		enterExistingSsnInput.sendKeys(ssn);
	}
	public void enterExistingStopServicePhone(String phone) {
	    setServiceStopPhone(phone);
//	    getServiceStopContinueButton.click();	
	}
	public void setServiceStopPhone(String phone) {
		getServiceStopPhoneInput.sendKeys(phone);
	}

	public void setReminderButton() {
		getReminderButton.click();	
	}
	public void setServiceStopContinueButton() {
		 getServiceStopContinueButton.click();	
	}

	public void getVerifyBillingButton() {
		getVerifyBillingButton.click();	
	}
	
	public void getBillingNextStepButton() {
		getBillingNextStepButton.click();	
	}
	
	
	public void closePopup() {
		if (popUp.isDisplayed())
			closeButton.click();
	}
	
	
	
	public void sleep() throws InterruptedException {
		pageManager.sleep(5000);
	}
}