package com.perficient.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.support.PageFactory;

public class PageObject {

	protected Log log = LogFactory.getLog(this.getClass());
	protected PageManager pageManager;
	public ExcelReader excelReader;
		// public CSVUtil csvUtil;
	
		public PageObject(PageManager pm, ExcelReader xl) {
		pageManager = pm;
		
		excelReader=xl;
	
		// csvUtil = csv;
		
		PageFactory.initElements(pageManager.getDriver(), this);
	}
	
}



