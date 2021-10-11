package ui.driver;

import httprequests.EndPoints;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.pageobjeccts.BasePage;

public class DriverFactory {

    private static WebDriver driver;
    private static WebDriverWait webDriverWait;

    /***
     * method to initialize webdriver instance
     */
    public static void setDriver(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
        driver.get(EndPoints.guiURL());
        BasePage.driver = driver;
        BasePage.webDriverWait = webDriverWait;
    }

    /***
     * method to get the webdriver instance
     * @return
     */
    public static WebDriver getDriver(){
        return driver;
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] addScreenshotAllure() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
