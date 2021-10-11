package com.ffm.pages;

import com.ffm.utils.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Random;

public class FFM_OT_Member_Create_POM extends TestBase {

    //Add super admin span web element
    @FindBy(xpath = "//*[@id=\"kt_aside_menu\"]/ul/li[2]/div/ul/li[3]/a/span")
    WebElement addSuperAdminSpanElement;

    //First name Field
    @FindBy(xpath = "//*[@id=\"kt_content\"]/div/div/form/div[1]/input")
    WebElement firstNameFieldElement;

    //Last name field
    @FindBy(xpath = "//*[@id=\"kt_content\"]/div/div/form/div[2]/input")
    WebElement lastNameFieldElement;

    //Email field
    @FindBy(xpath = "//*[@id=\"kt_content\"]/div/div/form/div[3]/input")
    WebElement emailFieldElement;

    //Password field
    @FindBy(xpath = "//*[@id=\"kt_content\"]/div/div/form/div[4]/input")
    WebElement passwordFieldElement;

    //Register button
    @FindBy(xpath = "//*[@id=\"kt_content\"]/div/div/form/button")
    WebElement registerButtonElement;

    //OT member list title
    @FindBy(xpath = "//*[@id=\"kt_content\"]/div/div/div/div[1]/div[1]/h3")
    WebElement otMemberListTitlElement;

    //Initialize the elements
    public FFM_OT_Member_Create_POM() {

        PageFactory.initElements(driver, this);

    }

    //Click on the add super admin span
    public void clickAddSuperAdminSpan() {

        addSuperAdminSpanElement.click();
    }

    //Input the fields
    public void registerOTmember(String firstName, String lastName, String password) {
        Random random= new Random();
        int id= random.nextInt(99999);
         String email = "test"+id+"@email.com";


        firstNameFieldElement.sendKeys(firstName);
        lastNameFieldElement.sendKeys(lastName);
        emailFieldElement.sendKeys(email);
        passwordFieldElement.sendKeys(password);

    }


    //Click on the register button
    public void clickRegisterBtn() {

        registerButtonElement.click();
    }



}
