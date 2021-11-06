package com.perficient.util;

import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestFTPTransfer {
    private TestCaseBase testCase = new TestCaseBase();
    private ExtentTest test = null;
    private CustomAssertion customAsserter = null;
    private FTPTransfer ftpTransfer;
    private String username;
    private String password;
    private String host;
    private int port;
    private DatabaseUtil dbUtility;
    private final PrintStream originalStdOut = System.out;
    private ByteArrayOutputStream consoleContent = new ByteArrayOutputStream();

    /**
     * Set up FTPTransfer Test Environment
     */
    @BeforeClass
    public void setUp() {
        test = ComplexReportFactory.getTest("Test FTP Transfer", "TEST", "TESTING" );
        ftpTransfer = new FTPTransfer(test);
        TestData.load("testdata_FTPTransferTest.properties");
        username = TestData.get("username");
        password = TestData.get("password");
        host = TestData.get("host");
        port = Integer.parseInt(TestData.get("port"));

    }

    /**
     * Tear down FTP Transfer test Environment
     */
    @AfterClass
    public void tearDown() {
        test = null;
        ftpTransfer = null;
    }

    /**
     * Test connection to FTP Server
     */
    @Test
    public void TestFTPServerConnection() {
        ftpTransfer.FTPServerConnection(host, port, username, password);
        Assert.assertEquals(this.consoleContent.toString(), "FTP URL is: "+port);
    }

//    @Test(dependsOnMethods = { "TestFTPServerConnection" })
//    public void TestUploadFTPFile() throws Exception {
//        ftpTransfer.uploadFTPFile("/Users/Greeley/Workspace/PerficientNagpurGDCFramework/testdata/Sample1.txt",
//                "Sample1.txt", "/Users/Greeley/Workspace/PerficientNagpurGDCFramework/testdata/");
//    }



}
