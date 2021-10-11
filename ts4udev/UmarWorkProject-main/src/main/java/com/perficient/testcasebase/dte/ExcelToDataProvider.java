package com.perficient.testcasebase.dte;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bouncycastle.util.Arrays.Iterator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.perficient.util.DatabaseUtil;
import com.perficient.util.ExcelReader;
import com.perficient.util.FTPTransfer;
import com.perficient.util.PageManager;
import com.perficient.util.PageObject;
import com.perficient.util.TestCaseBase;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelToDataProvider extends PageObject   {

	public ExcelToDataProvider(PageManager pm, ExcelReader xl) {
		super(pm, xl);
		// TODO Auto-generated constructor stub
	}

	public void loadExcelSheet()
	{
		excelReader.setDefaultExcelSheet();
	}



	public List<String> readDTEData(String sheetName, String columnName1,String columnName2,String columnName3){

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
		}
		System.out.println(dataList.toString());
		return dataList;
	}
}
