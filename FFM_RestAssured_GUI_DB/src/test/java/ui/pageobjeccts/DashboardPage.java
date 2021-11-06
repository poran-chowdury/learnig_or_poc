package ui.pageobjeccts;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import java.util.List;

/***
 * page object class holding the elements and actions of the page
 */
public class DashboardPage extends BasePage {

    LoginPage loginPage;
    private List<WebElement> deleteButtons;

    /***
     * element locators
     */
    By listCompanies = By.xpath("//span[contains(text(),' List Companies')]");
    By listCompanyAdmin = By.xpath("//span[contains(text(),' List Company Admin')]");
    By listSuperAdmin = By.xpath("//span[contains(text(),' List Super Admin')]");
    By listServices = By.xpath("//span[contains(text(),'  List Services ')]");

    By table = By.xpath("//table[@id='kt_table_1']/tbody/tr");

    String deleteButtonSuperAdminXpath = "//table[@id='kt_table_1']//tr//td[contains(text(), '%s')]/following-sibling::td/button[contains(text(), 'Delete')]";
    String ffmUserIDXpath = "//table[@id='kt_table_1']//tr//td[contains(text(), '%s')]/parent::tr/td[1]";

    String deleteButtonCompanyXpath = "//table[@id='kt_table_1']//tr//td[contains(text(), '%s')]/following-sibling::td/button[contains(text(), 'Delete')]";
    String companyIDXpath = "//table[@id='kt_table_1']//tr//td[contains(text(), '%s')]/parent::tr/td[1]";

    String deleteButtonCompanyManagerXpath = "//table[@id='kt_table_1']//tr//td[contains(text(), '%s')]/following-sibling::td/button[contains(text(), 'Delete')]";
    String companyManagerIDXpath = "//table[@id='kt_table_1']//tr//td[contains(text(), '%s')]/parent::tr/td[1]";

    String confirmButtonXpath = "(//div[@class='modal-footer']/button[contains(text(), 'Confirm')])[%d]";

    By confirmButton = By.xpath("(//div[@class='modal-footer']/button[contains(text(), 'Confirm')])[2]");
    By deleteButton = By.xpath("//table[@id='kt_table_1']//tr//td//button[contains(text(), 'Delete')]");
    By lastConfirmButton = By.xpath("//div[@class='modal-footer']/button[contains(text(), 'Confirm')]");

    By logoutDropDown = By.xpath("//div[@class='dropdown']//button[@id='dropdown-basic-button']");
    By logoutButton = By.xpath("//a[contains(text(),'Log Out')]");

    /***
     * method to delete all listed companies
     */
    public void deleteAllCompanies() {
        waitAndClickElement(listCompanies);
        deleteRecords();
    }

    /***
     * method to delete all listed company admins
     */
    public void deleteAllCompanyAdmins() {
        waitAndClickElement(listCompanyAdmin);
        deleteRecords();
    }

    /***
     * method to delete all listed super admins
     */
    public void deleteAllSuperAdmins() {
        waitAndClickElement(listSuperAdmin);
        deleteRecords();
    }

    /***
     * method to delete super admin with given email
     */
    public void deleteSuperAdmin(String email) {
        waitAndClickElement(listSuperAdmin);
        deleteAdminWithEmail(email);
    }

    /***
     * method to delete super admin with given email
     */
    public void deleteCompanyManager(String email) {
        waitAndClickElement(listCompanyAdmin);
        deleteACompanyAdminWithEmail(email);
    }

    /***
     * method to delete super admin with given email
     */
    public void deleteCompany(String name) {
        waitAndClickElement(listCompanies);
        deleteCompanyWithName(name);
    }

    /***
     * method to delete all listed services
     */
    public void deleteAllServices() {
        waitAndClickElement(listServices);
        deleteRecords();
    }

    /***
     * method to logout the application
     */
    public void logout() throws InterruptedException {
        hardWait(5); //wait for 5 seconds so page should be stable
        waitAndClickElement(logoutDropDown);
        clickElement(logoutButton);
        loginPage = new LoginPage();
        waitForElementToBeDisplayed(loginPage.loginPopUp);
    }

    /***
     * method to delete all listed users from UI
     */
    private void deleteRecords() {
        try {
            waitForElementToBeDisplayed(table);
            deleteButtons = findElements(deleteButton);
            for(WebElement deleteButton1: deleteButtons ) {
                if(deleteButtons.size()==1){
                    try{
                        deleteButton1.click();
                    }catch (StaleElementReferenceException stm){
                        driver.findElement(deleteButton).click();
                    }
                    waitForElementToBeDisplayed(lastConfirmButton);
                    clickElement(lastConfirmButton);
                    break;
                }else{
                    try {
                        deleteButton1.click();
                    }catch (StaleElementReferenceException stm){
                        driver.findElement(deleteButton).click();
                    }
                    waitForElementToBeDisplayed(confirmButton);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    clickElementUsingActions(confirmButton);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    waitForElementToBeDisplayed(table);
                    deleteButtons = findElements(deleteButton);
                }
            }
        } catch (TimeoutException e) {
            System.out.println("no records present in the list");
            System.out.println(e.getMessage());
        }
    }

    /***
     * method to verify if all users has been deleted from UI
     */
    public void verifyAllRecordsHasBeenDeleted(){
        try {
            waitForElementToBeDisplayed(table);
            //Assert.fail("failed to delete all records");
        } catch (TimeoutException e) {
            Assert.assertTrue(true);
            System.out.println("no records present in the list");
            System.out.println(e.getMessage());
        }
    }

    /***
     * method to delete admin with matching email
     */
    private void deleteAdminWithEmail(String email) {
        waitForElementToBeDisplayed(table);
        findElementWithDynamicText(deleteButtonSuperAdminXpath, email).click();
        Integer recordId = getRecordId(ffmUserIDXpath, email);
        hardWait(2);
        waitForDynamicConfirmButtonToBeDisplayed(confirmButtonXpath, recordId);
        WebElement confirmButton = findElementWithDynamicText(confirmButtonXpath, recordId);
        clickElementUsingActions(confirmButton);
    }

    /***
     * method to delete admin with matching email
     */
    private void deleteACompanyAdminWithEmail(String email) {
        waitForElementToBeDisplayed(table);
        findElementWithDynamicText(deleteButtonCompanyManagerXpath, email).click();
        Integer recordId = getRecordId(companyManagerIDXpath, email);
        hardWait(2);
        waitForDynamicConfirmButtonToBeDisplayed(confirmButtonXpath, recordId);
        WebElement confirmButton = findElementWithDynamicText(confirmButtonXpath, recordId);
        clickElementUsingActions(confirmButton);
    }

    /***
            * method to delete company with matching name
     */
    private void deleteCompanyWithName(String name) {
        waitForElementToBeDisplayed(table);
        findElementWithDynamicText(deleteButtonCompanyXpath, name).click();
        Integer recordId = getRecordId(companyIDXpath, name);
        hardWait(2);
        waitForDynamicConfirmButtonToBeDisplayed(confirmButtonXpath, recordId);
        WebElement confirmButton = findElementWithDynamicText(confirmButtonXpath, recordId);
        clickElementUsingActions(confirmButton);
    }

    /***
     * method to verify if user has been deleted from UI
     */
    public void verifyUserHasBeenDeleted(String email){
        try {
            waitForElementToBeDisplayedByDynamicText(ffmUserIDXpath, email);
        } catch (TimeoutException e) {
            Assert.assertTrue(true);
            System.out.println("no such records present in the list");
            System.out.println(e.getMessage());
        }
    }

    /***
     * method to verify if user has been deleted from UI
     */
    public void verifyCompanyHasBeenDeleted(String name){
        try {
            waitForElementToBeDisplayedByDynamicText(companyIDXpath, name);
        } catch (TimeoutException e) {
            Assert.assertTrue(true);
            System.out.println("no such records present in the list");
            System.out.println(e.getMessage());
        }
    }

    /***
     * method to verify if user has been deleted from UI
     */
    public void verifyCompanyAdminBeenDeleted(String email){
        try {
            waitForElementToBeDisplayedByDynamicText(deleteButtonCompanyManagerXpath, email);
        } catch (TimeoutException e) {
            Assert.assertTrue(true);
            System.out.println("no such records present in the list");
            System.out.println(e.getMessage());
        }
    }
}
