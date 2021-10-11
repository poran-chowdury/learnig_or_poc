package com.perficient.testCases.DTE;

import com.perficient.util.ComplexReportFactory;
import com.perficient.util.ExcelReader;
import com.perficient.util.TestCaseBase;
import com.relevantcodes.extentreports.ExtentTest;
import org.testng.annotations.Test;
import com.perficient.util.ExcelReader.*;

public class EmployeePricing extends TestCaseBase {
    ExtentTest employeePricingTest = ComplexReportFactory.getTest(
            "EmployeePricing",
            "EmployeePricing",
            "Automate checking employee pricing"
    );


    @Test(groups = { "firefox", "IEWin32", "ChromeWin32", "browserPercentageSpecified" })
    public void employeePricing() {
        Object excelData = readExcelData();

    }

    public Object readExcelData(){
        excelReader = new ExcelReader(employeePricingTest);
        excelReader.load("2019 FEP LEP_Validation_Tracking Sheet.xlsx");
        Object excelData = excelReader.getExcelData("Sheet1");
        return excelData;
    }



}
