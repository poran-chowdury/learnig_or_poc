package weathershopper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class DriverFactory {

    private static WebDriver driver;
    private static WebDriverWait webDriverWait;

    @BeforeTest
    public static void setDriver() {
        System.setProperty("webdriver.chrome.driver", "src//main//resources//chromedriver.exe");
//        System.setProperty("webdriver.chrome.driver", "/usr/lib/chromium-browser/chromedriver");

        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
        driver.get("https://weathershopper.pythonanywhere.com/");
        Base.driver = driver;
        Base.webDriverWait = webDriverWait;
    }

    @AfterTest
    public static void closeDriver() throws InterruptedException {
        Thread.sleep(5000);
        driver.close();
        driver.quit();
    }
}
