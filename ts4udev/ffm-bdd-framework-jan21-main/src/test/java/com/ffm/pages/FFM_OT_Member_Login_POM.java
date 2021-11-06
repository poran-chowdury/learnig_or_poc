package com.ffm.pages;

import com.ffm.utils.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class FFM_OT_Member_Login_POM extends TestBase {

    //WebElements of login page
    @FindBy(id="login")
    private WebElement emailField;

    @FindBy(id="password")
    private WebElement passwordField;

    @FindBy(xpath="//*[@id=\"formContent\"]/form/input[3]")
    private WebElement loginButton;

   //Initialize the elements
    public FFM_OT_Member_Login_POM(){
        PageFactory.initElements(driver, this);
    }

    //passing the email and password
    public void loginOtMember(String email, String password){
        emailField.sendKeys(email);
        passwordField.sendKeys(password);

    }

    //Clicking login button
    public void clickLoginButton(){
        loginButton.click();
    }

    //Login Assertion
    public void loginAssert() {
        String expectedResult= driver.findElement(By.xpath("//*[@id=\"kt_aside_menu\"]/ul/li[1]/a/span")).getText();
        String actualResult= "Dashboard";

        Assert.assertEquals(expectedResult, actualResult);
    }



}
