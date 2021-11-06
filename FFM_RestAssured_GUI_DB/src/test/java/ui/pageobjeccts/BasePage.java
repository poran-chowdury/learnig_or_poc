package ui.pageobjeccts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage {
    public static WebDriver driver;
    public static WebDriverWait webDriverWait;

    /***
     * wait for the element to be displayed on the UI matching the locator
     * @param locator
     * @return
     */
    protected void waitForElementToBeDisplayed(By locator){
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /***
     * wait for the element to be displayed on the UI matching the locator
     * @param xpath
     * @param recordID
     * @return
     */
    protected void waitForDynamicConfirmButtonToBeDisplayed(String xpath, Integer recordID){
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(xpath, recordID))));
    }

    /***
     * wait for the element to be displayed on the UI matching the locator identified by xpath after replacing the dynamic text
     * @param xpath
     * @param text
     * @return
     */
    protected void waitForElementToBeDisplayedByDynamicText(String xpath, String text){
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(xpath, text))));
    }

    protected Integer getRecordId(String xpath, String text){
        return Integer.parseInt(driver.findElement(By.xpath(String.format(xpath, text))).getText());
    }


    /***
     * wait for the presence of element on the UI matching the locator
     * @param locator
     * @return
     */
    protected void waitForElementToBePresent(By locator){
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /***
     * wait for the element to be clickable on the UI matching the locator
     * @param locator
     * @return
     */
    protected void waitForElementToBeClickable(By locator){
        webDriverWait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /***
     * waits for and click the element matching the locator
     * @param locator
     * @return
     */
    protected void waitAndClickElement(By locator){
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).click();
    }

    /***
     * method to enter text in the text box identified by the provided locator
     * @param text
     * @param locator
     */
    protected void typeInText(String text, By locator){
        driver.findElement(locator).sendKeys(text);
    }

    /***
     * click the element matching the locator
     * @param locator
     * @return
     */
    protected void clickElement(By locator){
        driver.findElement(locator).click();
    }

    /***
     * find and return all the elements matching the locator
     * @param locator
     * @return
     */
    protected List<WebElement> findElements(By locator){
        return driver.findElements(locator);
    }

    /***
     * find and return the elements matching the locator
     * @param locator
     * @return
     */
    protected WebElement findElement(By locator){
        return driver.findElement(locator);
    }

    protected void clickElementUsingActions(By locator){
        Actions actions = new Actions(driver);
        actions.moveToElement(findElement(locator)).click().perform();
    }

    protected void clickElementUsingActions(WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
    }

    protected WebElement findElementWithDynamicText(String xpath, String text){
        waitForElementToBeDisplayed(By.xpath(String.format(xpath, text)));
        return driver.findElement(By.xpath(String.format(xpath, text)));
    }

    protected WebElement findElementWithDynamicText(String xpath, Integer recordId){
        return driver.findElement(By.xpath(String.format(xpath, recordId)));
    }

    protected void hardWait(int second){
        try{
            Thread.sleep(second * 1000);
        } catch (Exception e){

        }
    }
}
