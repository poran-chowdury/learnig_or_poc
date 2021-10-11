package com.perficient.testcasebase.dte;


import java.util.ArrayList;
import java.util.List;

import com.perficient.util.DatabaseUtil;
import com.perficient.util.ExcelReader;
import com.perficient.util.FTPTransfer;
import com.perficient.util.PageManager;
import com.perficient.util.PageObject;

public class ExcelBasePage extends PageObject {
	
	public ExcelBasePage(PageManager pm, ExcelReader xl) {
		super(pm,xl);
	}
	
	public void loadExcelSheet()
	{
		excelReader.setDefaultExcelSheet();
	}
	
	public List<String> readData(String sheetName, String columnName1,String columnName2,String columnName3,String columnName4){

		List<String> dataList = new ArrayList<>();
		String data;
		for(int i=1;i<=excelReader.getRowCount(sheetName);i++)
		{
			 data = excelReader.getCellData(sheetName, columnName1, i);
			 dataList.add(data);
			 data = excelReader.getCellData(sheetName, columnName2, i);
			 dataList.add(data);
			 data = excelReader.getCellData(sheetName, columnName3, i);
			 dataList.add(data);
			 data = excelReader.getCellData(sheetName, columnName4, i);
			 dataList.add(data);
		}
		System.out.println(dataList.toString());
		return dataList;
	}
	
	

	public void setData(List<String> dataList, String resultSheetName, String columnName1,String columnName2,String columnName3,String columnName4) {
		
		excelReader.addSheet(resultSheetName);
		excelReader.addColumn(resultSheetName, columnName1);
		excelReader.addColumn(resultSheetName, columnName2);
		excelReader.addColumn(resultSheetName, columnName3);
		excelReader.addColumn(resultSheetName, columnName4);
		int j=4;
		for(int i=2;i<11;i++)
		{	
			excelReader.setCellData(resultSheetName, columnName1, i, dataList.get(j));
			j++;
			excelReader.setCellData(resultSheetName, columnName2, i, dataList.get(j));
			j++;
			excelReader.setCellData(resultSheetName, columnName3, i, dataList.get(j));
			j++;
			excelReader.setCellData(resultSheetName, columnName4, i, dataList.get(j));
			j++;
		}
	}
}
