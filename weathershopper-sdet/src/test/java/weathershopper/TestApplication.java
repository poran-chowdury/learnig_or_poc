package weathershopper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class TestApplication extends Base {

    String temperatureId = "temperature";
    Integer degree = 0;

    String moisturizerButtonXpath = "/html/body/div/div[3]/div[1]/a";
    String sunscreenButtonXpath = "/html/body/div/div[3]/div[2]/a";
    String cartButtonXpath = "/html/body/nav/ul/button";
    String paymentSuccessXpath = "/html/body/div/div[2]/p";
    String clickOnPayWithCardXpath = "/html/body/div[1]/div[3]/form/button/span";
    String emailAddress = "email";
    String cardNumber = "card_number";
    String monthYear = "cc-exp";
    String cvc = "cc-csc";
    String zipCode = "billing-zip";
    String payButtonXpath = "submitButton";

    String productOneName = "/html/body/div[1]/div[2]/div[1]/p[1]";
    String productTwoName = "/html/body/div[1]/div[2]/div[2]/p[1]";
    String productThreeName = "/html/body/div[1]/div[2]/div[3]/p[1]";
    String productFourName = "/html/body/div[1]/div[3]/div[1]/p[1]";
    String productFiveName = "/html/body/div[1]/div[3]/div[2]/p[1]";
    String productSixName = "/html/body/div[1]/div[3]/div[3]/p[1]";

    @Test(priority = 1)
    public void selectProduct() {
        verifyTemperatureAndSelectCreme();
        clickOnCart();
        makePayment();
        verifyPayment();
    }

    private void makePayment() {
        waitAndClickElement(By.xpath(clickOnPayWithCardXpath));
        driver.switchTo().frame(findElement(By.name("stripe_checkout_app"))).navigate();
        typeInText("test@yopmail.com", By.id(emailAddress));
        typeInText(5200828282828210L, By.id(cardNumber));
        typeInText(822L, By.id(monthYear));
        typeInText(123L, By.id(cvc));
        typeInText(8000L, By.id(zipCode));
        clickElement(By.id(payButtonXpath));
    }

    private void verifyPayment() {
        String paymentText = findElement(By.className("text-justify")).getText();
        Assert.assertTrue(paymentText.contains("successful"));
    }

    private void verifyTemperatureAndSelectCreme() {
        waitForElementToBeDisplayed(By.id(temperatureId));
        WebElement temperatureValue = findElement(By.id(temperatureId));
        degree = getDegree(temperatureValue);

        if (degree < 19) {
            getMoisturizers();
        } else if (degree > 34) {
            getSunscreen();
        }
    }

    private void getMoisturizers() {
        waitAndClickElement(By.xpath(moisturizerButtonXpath));
        getProductList("aloe", "almond");
    }

    private void getSunscreen() {
        waitAndClickElement(By.xpath(sunscreenButtonXpath));
        getProductList("spf-50", "spf-30");
    }

    private void getProductList(String productOne, String productTwo) {
        HashMap<String, String> getMoisturizerNamePriceMap = getProductNamePriceMap();
        HashMap<String, String> getMoisturizerNameButtonMap = getProductNameButtonMap();

        HashMap<Integer, String> aloePriceNameMap = new HashMap<Integer, String>();
        HashMap<Integer, String> almondPriceNameMap = new HashMap<Integer, String>();

        for (Map.Entry<String, String> entry : getMoisturizerNamePriceMap.entrySet()) {
            WebElement webElementName = findElement(By.xpath(entry.getKey()));
            String name = webElementName.getText().toLowerCase();

            WebElement webElementValue = findElement(By.xpath(entry.getValue()));
            String[] prices = webElementValue.getText().split(" ");
            Integer price = Integer.parseInt(prices[prices.length - 1]);

            if (name.contains(productOne)) {
                aloePriceNameMap.put(price, entry.getKey());
            } else if (name.contains(productTwo)) {
                almondPriceNameMap.put(price, entry.getKey());
            }

        }

        Integer aloePrice = 9999;
        for (Map.Entry<Integer, String> entry : aloePriceNameMap.entrySet()) {
            if (entry.getKey() < aloePrice) aloePrice = entry.getKey();
        }

        Integer almondPrice = 9999;
        for (Map.Entry<Integer, String> entry : almondPriceNameMap.entrySet()) {
            if (entry.getKey() < almondPrice) almondPrice = entry.getKey();
        }

        clickElement(By.xpath(getMoisturizerNameButtonMap.get(aloePriceNameMap.get(aloePrice))));
        clickElement(By.xpath(getMoisturizerNameButtonMap.get(almondPriceNameMap.get(almondPrice))));
    }

    private void clickOnCart() {
        clickElement(By.xpath(cartButtonXpath));
    }

    private Integer getDegree(WebElement temperatureValue) {
        return Integer.parseInt(temperatureValue.getText().split(" ")[0]);
    }

    private HashMap<String, String> getProductNameButtonMap() {
        HashMap<String, String> productMap = new HashMap<String, String>() {
        };

        productMap.put(productOneName, "/html/body/div[1]/div[2]/div[1]/button");
        productMap.put(productTwoName, "/html/body/div[1]/div[2]/div[2]/button");
        productMap.put(productThreeName, "/html/body/div[1]/div[2]/div[3]/button");
        productMap.put(productFourName, "/html/body/div[1]/div[3]/div[1]/button");
        productMap.put(productFiveName, "/html/body/div[1]/div[3]/div[2]/button");
        productMap.put(productSixName, "/html/body/div[1]/div[3]/div[3]/button");

        return productMap;
    }

    private HashMap<String, String> getProductNamePriceMap() {
        HashMap<String, String> productMap = new HashMap<String, String>() {
        };

        productMap.put(productOneName, "/html/body/div[1]/div[2]/div[1]/p[2]");
        productMap.put(productTwoName, "/html/body/div[1]/div[2]/div[2]/p[2]");
        productMap.put(productThreeName, "/html/body/div[1]/div[2]/div[3]/p[2]");
        productMap.put(productFourName, "/html/body/div[1]/div[3]/div[1]/p[2]");
        productMap.put(productFiveName, "/html/body/div[1]/div[3]/div[2]/p[2]");
        productMap.put(productSixName, "/html/body/div[1]/div[3]/div[3]/p[2]");

        return productMap;
    }
}