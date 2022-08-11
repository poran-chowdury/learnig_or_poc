package weathershopper;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Base extends DriverFactory {

    public static WebDriver driver;
    public static WebDriverWait webDriverWait;

    protected void waitForElementToBeDisplayed(By locator) {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitAndClickElement(By locator) {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).click();
    }

    protected void typeInText(String text, By locator) {
        driver.findElement(locator).sendKeys(text);
    }

    protected void typeInText(Long number, By locator) {
        for (char ch : String.valueOf(number).toCharArray())
            switch (Integer.parseInt(String.valueOf(ch))) {
                case 1:
                    driver.findElement(locator).sendKeys(Keys.NUMPAD1);
                    break;
                case 2:
                    driver.findElement(locator).sendKeys(Keys.NUMPAD2);
                    break;
                case 3:
                    driver.findElement(locator).sendKeys(Keys.NUMPAD3);
                    break;
                case 4:
                    driver.findElement(locator).sendKeys(Keys.NUMPAD4);
                    break;
                case 5:
                    driver.findElement(locator).sendKeys(Keys.NUMPAD5);
                    break;
                case 6:
                    driver.findElement(locator).sendKeys(Keys.NUMPAD6);
                    break;
                case 7:
                    driver.findElement(locator).sendKeys(Keys.NUMPAD7);
                    break;
                case 8:
                    driver.findElement(locator).sendKeys(Keys.NUMPAD8);
                    break;
                case 9:
                    driver.findElement(locator).sendKeys(Keys.NUMPAD9);
                    break;
                case 0:
                    driver.findElement(locator).sendKeys(Keys.NUMPAD0);
                    break;
            }
    }

    protected void clickElement(By locator) {
        driver.findElement(locator).click();
    }

    protected WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

}
