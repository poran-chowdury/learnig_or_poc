package ch.selise.paa;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class AmazonWebsiteTest extends DriverSetup {

    public static String base_url = "https://www.amazon.com/";

    @Test
    public static void runTest() throws InterruptedException {
        preWork();
        caseOne();
        caseTwo();
        caseThree();

    }

    private static void preWork() throws InterruptedException {
        setDriver();
        driver.get(base_url);
        driver.manage().window().maximize();
        Thread.sleep(1000);
    }

    /**
     * CASE 1
     * Select the element from the drop-down.
     *
     * @throws InterruptedException
     */
    private static void caseOne() throws InterruptedException {
        driver.findElement(By.xpath("//select[@id='searchDropdownBox']")).click();
        Thread.sleep(5000);
    }


    /**
     * CASE 2
     * Insert the text for search and click the search button
     *
     * @throws InterruptedException
     */
    private static void caseTwo() throws InterruptedException {
        Select dropOption = new Select(driver.findElement(By.xpath("//select[@id='searchDropdownBox']")));
        dropOption.selectByValue("search-alias=baby-products-intl-ship");
        Thread.sleep(5000);

        driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("Bag");
        Thread.sleep(5000);

        driver.findElement(By.xpath("//input[@id='nav-search-submit-button']")).click();
        Thread.sleep(5000);
    }

    /**
     * CASE 3
     * Close the browser
     *
     * @throws InterruptedException
     */
    private static void caseThree() {
        close();
    }
}
