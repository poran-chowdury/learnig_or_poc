package com.ffm.stepDefinitions;

import com.ffm.utils.TestBase;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks extends TestBase {

    //Global Hooks
    @Before
    public void setup(){
        initializeDriver();

    }

    //Tagged Hooks
//    @Before("@FunctionalTest")
//    public void taggedHooks(){
//        initializeDriver();
//
//    }

    @After
    public void tearDown(){
        try{
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        driver.quit();

    }
}
