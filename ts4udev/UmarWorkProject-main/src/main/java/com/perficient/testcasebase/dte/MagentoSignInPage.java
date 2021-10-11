package com.perficient.testcasebase.dte;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.perficient.util.DatabaseUtil;
import com.perficient.util.ExcelReader;
import com.perficient.util.FTPTransfer;
//import com.perficient.insite.basepages.InsiteSignInPage;
import com.perficient.util.PageManager;
import com.perficient.util.PageObject;

public class MagentoSignInPage extends PageObject {
	public static String SIGNINTITLE = "Customer Login";
	public static String HOMEPAGETITLE = "Home Page";
	public static String MYACCOUNTTITLE = "My Account";

	@FindBy(xpath = "//div[@class='panel header']//a[contains(text(),'Sign In')]")
	public WebElement clickSignIn;
	
	@FindBy(xpath = "//input[@id='email']")
	public WebElement userName;

	@FindBy(xpath = "//input[@id='pass']")
	public WebElement password;
	
	@FindBy(xpath = "//button[@id='send2']")
	public WebElement signInButton;

	public MagentoSignInPage(PageManager pm, ExcelReader excelReader) {
		super(pm, excelReader);
	}

	public boolean titleContains(String title) {

		return pageManager.getTitle().contains(title);
	}
	
	public void open(String url) {
		pageManager.navigate(url);
	}

	public void clickOnSignIn() {
		pageManager.click(clickSignIn);
	}
	
	public void navigateToSignInPage(String navigateToSignInPage) {
		pageManager.navigate(navigateToSignInPage);
	}
	
	public void signIn(String usr, String pwd) throws InterruptedException {
		pageManager.sendKeys(userName, usr);
		pageManager.sendKeys(password, pwd);
		pageManager.click(signInButton);
	}
}
