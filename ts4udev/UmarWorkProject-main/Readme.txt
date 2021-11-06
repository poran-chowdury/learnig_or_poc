This file will list down the changes done in Framework.

===========================================================================
5th August 2020
---------------------------------------------------------------------------
1. Updated the Presentation.
2. Updated the POM.XML file.
===================================XXXXXXX==================================

===========================================================================
27 May 2020
---------------------------------------------------------------------------
1. NEW ASSERTION FLOW: One should add below mandatory assertion method at the end of each test methods.
Assert.assertEquals(ComplexReportFactory.getTest(testName).getRunStatus(), Status.PASS);
> PURPOSE: This will resolve the issue of skipping of remaining test after a test failure.
> DEMO: You can refer the assertion method which needs to added as per new flow in the demo test cases method.

2. AUTOMATED BROWSER DRIVER MANAGEMENT: Added the "WebDriverManager" capability to get rid of manually downloading and managing browser drivers for each operating systems.
> PURPOSE: By using this we can avoid all the manual steps to check the browser version every time and find out the compatible browser binaries/exe and add it in lib folder.
> PURPOSE: "WebDriverManager" helps us to manage driver related settings with ease. WebDriverManager gets the browser version and downloads relevant binaries/exe automatically during test execution.
> DEMO: It is already added in TestCaseBase. You can directly run test in your machine on any browser.

3. REUSABLE UTILITY METHODS: Added a bunch of useful reusable methods (23 to 25 i guess) in pageManger. Names of the method are self explanatory but you can find more about their purpose in their respective comment blocks.
> PURPOSE: To ease the automation script development.
> Listing newly added Method Names:
clickByJavaScriptExecutor(), waitForPageL10oaded(), switchToWindowwithTitle(), closeWindowWithTitle(), click() by locator, sendKeys() by locator, clickByAction()
isElementVisible(), waitForPageLoaded(), scrollToVisible(), scrollToVisibleAndClick(), scrollToTop(), clickHoldAndRelease(), waitElementVisible(), waitForLoad()
goBackToPrevious(), pageRefresh(), getList(), hoverElement(), verifyElementExists(), switchToWindow(), switchToMainWindow(), waitForSeconds()
===================================XXXXXXX==================================


===========================================================================
27 May 2020 - Email functionality host was changed to gmail from outlook and default email address was set.
---------------------------------------------------------------------------
1. ZipAndMail utility class was updated and host was changed to gmail from outlook because perficient has introduced authentication hence we could not send emails from perficient account through selenium script.
2. New gmail account was created with the name "perficientnagpurgdc@gmail.com" and was set in mailport.properties file in fromEmailId. Now all the emails will be send from this email id.
3. Note - You will not be able to receive email on perficient email id due to added authentication.
===================================XXXXXXX==================================


===========================================================================
Feb 2020 - Mobile Testing Code was integrated.
---------------------------------------------------------------------------
1. Two new methods were introduced in TestCaseBase Util class for integrating Mobile Device testing.
2. Method names are setMobileByCBT() & setMobileByBrowserStack().
3. Replace your old TestCaseBase file with this new one to get the latest updates.
===================================XXXXXXX==================================