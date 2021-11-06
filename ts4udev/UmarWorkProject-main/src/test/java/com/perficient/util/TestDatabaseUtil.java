package com.perficient.util;

import com.relevantcodes.extentreports.ExtentTest;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class TestDatabaseUtil {
    private ExtentTest test;
    private String username;
    private String password;
    private String url;
    private String environment;
    private String selectQuery;
    private String updateQuery;
    private DatabaseUtil dbUtility;

    /**
     * setup needed data for test
     */
    @BeforeClass
    public void setUp() {
        this.test = ComplexReportFactory.getTest("TestDatabaseUtil", "TEST", "TESTING");
        dbUtility = new DatabaseUtil(this.test);
        TestData.load("testdata_DatabaseTest.properties");
        username = TestData.get("dbUsername");
        password = TestData.get("dbPassword");
        url = TestData.get("dbUrl");
        environment = TestData.get("dbEnvironment");
        selectQuery = TestData.get("selectQuery");
        updateQuery = TestData.get("updateQuery");
    }

    /**
     * Tear down testing environment
     * @throws SQLException
     */
    @AfterClass
    public void tearDown() throws SQLException {
        this.test = null;
        if (dbUtility.connection.isValid(10))
            dbUtility.connection.close();
        username = null;
        password = null;
        url = null;
        environment = null;
        selectQuery = null;
        updateQuery = null;
    }

    /**
     * Test connecting to the DB
     * @throws SQLException
     */
    @Test
    public void TestConnectToDB() throws SQLException {
        dbUtility.connectToDb(username, password, url, environment);
        Assert.assertTrue(dbUtility.connection.isValid(10));
    }

    /**
     * Test Select query in database util
     */
    @Test
    public void TestSelectQuery() throws ClassNotFoundException {
        try {
            dbUtility.selectQuery(selectQuery);
            Assert.assertTrue(true);
        } catch (ClassNotFoundException e) {
            Assert.assertTrue(false);
        }

    }

    /**
     * Test Update query in database util
     */
    @Test
    public void TestUpdateQuery() {
        try {
            dbUtility.updateQuery(updateQuery);
            Assert.assertTrue(true);
        } catch (ClassNotFoundException e) {
            Assert.assertTrue(false);
        }
    }

    /**
     * Test closing the DB in DatabaseUtil
     * @throws SQLException
     */
    @Test
    public void TestCloseDB() throws SQLException {
        dbUtility.closeDb();
        Assert.assertTrue(dbUtility.connection.isClosed());
    }

    @Test
    public void TestConnectDBWithAccess() throws SQLException {
        dbUtility.connectToDb(username, password, url, environment);
        Assert.assertTrue(dbUtility.connection.isValid(10));
    }
}
