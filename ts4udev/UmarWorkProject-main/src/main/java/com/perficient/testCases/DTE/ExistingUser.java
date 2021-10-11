package com.perficient.testCases.DTE;

import com.perficient.testcasebase.dte.DTEHomepage;
// import com.perficient.util.CSVUtil;
import com.perficient.util.TestCaseBase;

import java.io.IOException;
import java.util.Iterator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



public class ExistingUser extends TestCaseBase {


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
		
	}
	
	 @Test(priority = 30)
	    public void testEnterExistingUser() throws InterruptedException{
			DTEHomepage dteHomepage = new DTEHomepage(pageManager, excelReader);
			 dteHomepage.enterExistingUser("BP1101025177@DTEACCEPT.COM", "TEST1234");
			 dteHomepage.sleep();
			 customAssertion.assertTrue(pageManager.getTitle().contains(DTEHomepage.TITLE));
		
			 
	 }
	 
		@Test(priority = 40, groups = { "firefox", "IEWin32", "ChromeWin32", "browserPercentageSpecified" })
		public void testStopServiceLink() throws Exception {
			 DTEHomepage dteHomepage = new DTEHomepage(pageManager, excelReader);
			dteHomepage.getStopServiceLink();
			dteHomepage.sleep();
			customAssertion.assertTrue(pageManager.getText(dteHomepage.getStopModalTitle).contains(DTEHomepage.STOPMODALTITLE));
			dteHomepage.getStopServiceModalButton();
			dteHomepage.sleep();
			dteHomepage.getAddressRadioButton();
			dteHomepage.sleep();
			dteHomepage.getAddressCountinueButton();
			dteHomepage.sleep();
			dteHomepage.getNextStepStopServiceAddressButton();
			dteHomepage.sleep();
		}
		 @Test(priority = 50)
		    public void testEnterIdentification() throws InterruptedException {
				DTEHomepage dteHomepage = new DTEHomepage(pageManager,  excelReader);
				 dteHomepage.enterExistingSsn("001042817" );
				 dteHomepage.sleep();	 
		 }
		 
		 @Test(priority = 60)
		    public void testEnterServiceStopDate() throws InterruptedException {
				DTEHomepage dteHomepage = new DTEHomepage(pageManager, excelReader);
				 dteHomepage.enterServiceStopDate("11/19/2020" );
				 dteHomepage.sleep();
				 dteHomepage.sleep();
				 dteHomepage.enterExistingStopServicePhone("111.111.1111");
				 dteHomepage.sleep();
				 dteHomepage.setServiceStopContinueButton();
				 dteHomepage.sleep();
				 customAssertion.assertTrue(pageManager.getText(dteHomepage.getReminderModal).contains(DTEHomepage.REMINDERMODALTITLE));
				 dteHomepage.setReminderButton();
				 dteHomepage.sleep();
				 dteHomepage.enterAddress("16883 Santa Rosa Dr", "Detroit", "48221");
				 dteHomepage.enterExistingState();
				 dteHomepage.getVerifyBillingButton();
				 dteHomepage.sleep();
				 dteHomepage.getBillingNextStepButton();
				 dteHomepage.sleep();
				 
		 }
		
		 @Test(priority = 60)
		    public void testEnterServiceStopDate1() throws InterruptedException {
				DTEHomepage dteHomepage = new DTEHomepage(pageManager, excelReader);
				 dteHomepage.enterServiceStopDate("11/19/2020" );
				 dteHomepage.sleep();
				 dteHomepage.sleep();
				 dteHomepage.enterExistingStopServicePhone("111.111.1111");
				 dteHomepage.sleep();
				 dteHomepage.setServiceStopContinueButton();
				 dteHomepage.sleep();
				 customAssertion.assertTrue(pageManager.getText(dteHomepage.getReminderModal).contains(DTEHomepage.REMINDERMODALTITLE));
				 dteHomepage.setReminderButton();
				 dteHomepage.sleep();
				 dteHomepage.enterAddress("16883 Santa Rosa Dr", "Detroit", "48221");
				 dteHomepage.enterExistingState();
				 dteHomepage.getVerifyBillingButton();
				 dteHomepage.sleep();
				 dteHomepage.getBillingNextStepButton();
				 dteHomepage.sleep();
				 
		 }

}

