package com.perficient.util;

import com.relevantcodes.extentreports.ExtentReports;
import org.apache.xmlbeans.impl.xb.ltgfmt.TestCase;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestComplexReportFactory {
    private ComplexReportFactory reportFactory = new ComplexReportFactory();
    private TestCaseBase testCaseBase = new TestCaseBase();

    /**
     * Set up Complex Report Factory testing environment
     */
    @BeforeClass
    public void setUp() {
    }

    /**
     * Tear down Complex Report Factory testing environment
     */
    @AfterClass
    public void tearDown() {
        reportFactory = null;
    }

}
