package com.perficient.testCases.DTE;

import com.perficient.testcasebase.dte.DTEHomepage;
import com.perficient.util.SystemUtil;
// import com.perficient.util.CSVUtil;
import com.perficient.util.TestCaseBase;
import java.io.IOException;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.read.biff.File;

//import java.io.File;
import java.io.*;
import java.util.Iterator;
import java.util.Properties;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SampleErrorDTETest   extends TestCaseBase {
	
	
	@Test(priority = 40)
    public void testStreetAddressError() throws InterruptedException{
		DTEHomepage dteHomepage = new DTEHomepage(pageManager, excelReader);
		 
		 dteHomepage.open();
			dteHomepage.getStartedLink();
			dteHomepage.sleep();
			dteHomepage.getStartServiceButton();
			dteHomepage.sleep();
			dteHomepage.getCountinueButton();
			dteHomepage.sleep();
			//dteHomepage.closePopup();
			//dteHomepage.enterAddress("#542 Yorkshire Dr, APT #84,", "Detroit", "48228");
			 Properties PROPERTIES_RESOURCES = SystemUtil.loadPropertiesResources("/testdata_dte.properties");
				String ADDRESS = PROPERTIES_RESOURCES.getProperty("dte3.address");
				String CITY = PROPERTIES_RESOURCES.getProperty("dte3.city");
				String ZIP = PROPERTIES_RESOURCES.getProperty("dte3.zip");
				System.out.println("ADDRESS , CITY, ZIP" + ADDRESS + CITY + ZIP);
				dteHomepage.enterAddress(ADDRESS,CITY,ZIP);
	 	    //customAssertion.assertTrue(pageManager.getTextElement(dteHomepage.streetAddressError).contains(DTEHomepage.STREETADDRESSERROR));
			customAssertion.assertTrue(pageManager.getTextElement(dteHomepage.selectVerifiedAddress).contains(DTEHomepage.SELECTVERIFIEDADDRESS));
 }
		// dteHomepage.clickVerifyAddressButon();
		// dteHomepage.sleep();
		// dteHomepage.getAddressVerificationContinueButton();
		// dteHomepage.sleep();
		// dteHomepage.getServiceAddressNextStep();
		// dteHomepage.sleep();
		// customAssertion.assertTrue(pageManager.getText(dteHomepage.getCustomerType).contains(DTEHomepage.CUSTOMERTYPE));
		// dteHomepage. getContinueCustomerType();
		// dteHomepage.sleep();
	
 @Test(priority = 50)
    public void testZipCodeError() throws InterruptedException{
		DTEHomepage dteHomepage = new DTEHomepage(pageManager, excelReader);
		dteHomepage.open();
		dteHomepage.getStartedLink();
		dteHomepage.sleep();
		dteHomepage.getStartServiceButton();
		dteHomepage.sleep();
		dteHomepage.getCountinueButton();
		dteHomepage.sleep();
		//dteHomepage.closePopup();
		 //dteHomepage.enterAddress("12011 Ashton Ave", "Detroit", " ");
		Properties PROPERTIES_RESOURCES = SystemUtil.loadPropertiesResources("/testdata_dte.properties");
		String ADDRESS = PROPERTIES_RESOURCES.getProperty("dte4.address");
		String CITY = PROPERTIES_RESOURCES.getProperty("dte4.city");
		String ZIP = PROPERTIES_RESOURCES.getProperty("dte4.zip");
		System.out.println("ADDRESS , CITY, ZIP" + ADDRESS + CITY + ZIP);
		dteHomepage.enterAddress(ADDRESS,CITY,ZIP);
		 //customAssertion.assertTrue(pageManager.getTextElement(dteHomepage.zipCodeError).contains(DTEHomepage.ZIPCODEERROR));
		 customAssertion.assertTrue(pageManager.getTextElement(dteHomepage.selectVerifiedAddress).contains(DTEHomepage.SELECTVERIFIEDADDRESS));
 }
 
 /*Workbook workbook = null;
	Sheet sh1;
	int numrow;
	String username;
	String password;
	
 @DataProvider(name="testdata")
 public Object[][] TestDataFeed(){
  String filepath = "C://UsersSonali.Hatapaki//Desktop//POCDET//DTE%20Nagpur//testdata";
  String fileName = "AddressDataDriven.xls";
  File file = new File(filepath+"/"+fileName);
 // File inputWorkbook = new File(location);
  Workbook w;
  try {
      w = Workbook.getWorkbook(file);
     
		 
 // load sheet in my case I am referring to first sheet only
 sh1= w.getSheet(0);
  
 // get number of rows so that we can run loop based on this
 numrow=  sh1.getRows();
 }
 catch (Exception e)
  
 {
 e.printStackTrace();
 }
  
 
//Create 2 D array and pass row and columns
Object [][] addressbookdata=new Object[numrow][sh1.getColumns()];

//This will run a loop and each iteration  it will fetch new row
for(int i=0;i<numrow;i++){

//Fetch first row username
addressbookdata[i][0]=sh1.getCell(0,i).getContents();
//Fetch first row password
addressbookdata[i][1]=sh1.getCell(1,i).getContents();

}

//Return 2d array object so that test script can use the same
return addressbookdata;
}*/

}
