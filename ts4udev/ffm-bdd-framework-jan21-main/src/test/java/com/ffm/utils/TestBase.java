package com.ffm.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    //Global variables

    public static WebDriver driver;
    public static Properties prop;

    //Load the properties file
    public TestBase(){
        try{
            prop= new Properties();
            FileInputStream inputProperties= new FileInputStream("G:\\FFM_BDD_Automation\\src\\test\\java\\com\\ffm\\config\\config.properties");
            prop.load(inputProperties);

        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }


    //Initialize the drivers
    public static void initializeDriver(){
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();

        String browserName= prop.getProperty("browser");

        if(browserName.equals("chrome")){
            driver=new ChromeDriver();
        }else if(browserName.equals("firefox")){
            driver=new FirefoxDriver();
        }else{
            System.out.println("Driver's information not found");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(prop.getProperty("baseURL"));

    }

}
