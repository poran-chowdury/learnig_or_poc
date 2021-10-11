package com.ffm.runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/com/ffm/features/",
        glue = "com/ffm/stepDefinitions",
//        tags = "@FunctionalTest",
        monochrome = true, //It displays the console output in a proper readable format
        dryRun = false, //For debugging
        plugin = {"pretty","json:SimpleReporting/cucumber.json"} //It will generate cucumber report

)

public class TestRunner extends AbstractTestNGCucumberTests {


}
