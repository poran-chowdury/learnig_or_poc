package ui.pageobjeccts;

import org.openqa.selenium.By;

/***
 * page object class holding the elements and actions of the page
 */
public class LoginPage extends BasePage{

    /***
     * element locators
     */
    By usernameTextBox = By.id("login");
    By passwordTextBox = By.id("password");
    By loginPopUp = By.xpath("//div[@id='formContent']");
    By loginButton = By.xpath("//input[@type='submit']");

    /***
     * method to login with provided username and password
     * @param username
     * @param password
     * @return
     */
    public DashboardPage login(String username, String password){
        waitForElementToBeDisplayed(usernameTextBox);
        typeInText(username, usernameTextBox);
        typeInText(password, passwordTextBox);
        clickElement(loginButton);
        return new DashboardPage();
    }
}
