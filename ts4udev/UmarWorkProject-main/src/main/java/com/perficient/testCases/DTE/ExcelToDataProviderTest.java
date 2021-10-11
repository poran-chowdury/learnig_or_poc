package com.perficient.testCases.DTE;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.perficient.testcasebase.dte.DTEHomepage;
import com.perficient.testcasebase.dte.ExcelBasePage;
import com.perficient.testcasebase.dte.ExcelToDataProvider;
import com.perficient.util.SystemUtil;
import com.perficient.util.TestCaseBase;
import com.perficient.util.TestData;

public class ExcelToDataProviderTest extends TestCaseBase {
	@Test()
	public void TC001() throws InterruptedException, ClassNotFoundException
	{
		String sheetName = TestData.get("sheetName");
		String columnName1 = TestData.get("columnName1");
		String columnName2 = TestData.get("columnName2");
		String columnName3 = TestData.get("columnName3");
		
		ExcelToDataProvider xel = new ExcelToDataProvider(pageManager, excelReader);
		
		xel.loadExcelSheet();
		
		List<String> dataList = xel.readDTEData(sheetName, columnName1, columnName2, columnName3);
		
		for(int i=0;i<dataList.size();i++){
		    System.out.println(dataList.get(i));
		} 
		}
}
