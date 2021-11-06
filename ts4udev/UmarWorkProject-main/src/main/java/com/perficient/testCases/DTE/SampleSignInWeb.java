package com.perficient.testCases.DTE;

import com.perficient.testcasebase.dte.MagentoSignInPage;
import com.perficient.util.ComplexReportFactory;
import com.perficient.util.TestCaseBase;
import com.perficient.util.TestData;
import com.relevantcodes.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SampleSignInWeb extends TestCaseBase {

	@Test(groups = { "firefox", "IEWin32", "ChromeWin32", "browserPercentageSpecified" })
	public void signIn() throws Exception {

		String url = TestData.get("url");
		String username = TestData.get("username");
		String password = TestData.get("password");

		MagentoSignInPage magentoSignInPage = new MagentoSignInPage(pageManager, excelReader);
		magentoSignInPage.open(url);
		customAssertion.assertTrue(pageManager.getTitle().contains(MagentoSignInPage.HOMEPAGETITLE));

		magentoSignInPage.clickOnSignIn();
		customAssertion.assertTrue(pageManager.getTitle().contains(MagentoSignInPage.SIGNINTITLE));

		magentoSignInPage.signIn(username, password);
		customAssertion.assertTrue(pageManager.getTitle().contains(MagentoSignInPage.HOMEPAGETITLE));

		//Mandatory Assertion
		Assert.assertEquals(ComplexReportFactory.getTest(testName).getRunStatus(), Status.PASS);
	}
}
