package com.ffm.stepDefinitions;

import com.ffm.pages.FFM_OT_Member_Create_POM;
import com.ffm.pages.FFM_OT_Member_Login_POM;
import com.ffm.utils.TestBase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FFM_OT_Member_Create_Steps extends TestBase {
    FFM_OT_Member_Login_POM login;
    FFM_OT_Member_Create_POM ot_member_create;

    @Given("As an FFM OT member I should logged in the FFM platform")
    public void as_an_ffm_ot_member_i_should_logged_in_the_ffm_platform() {
        // Write code here that turns the phrase above into concrete actions
        login= new FFM_OT_Member_Login_POM();
        login.loginOtMember(prop.getProperty("email"),prop.getProperty("password"));
        login.clickLoginButton();

    }

    @When("I click on the add super admin span")
    public void i_click_on_the_add_super_admin_span() {
        // Write code here that turns the phrase above into concrete actions
        ot_member_create= new FFM_OT_Member_Create_POM();
        ot_member_create.clickAddSuperAdminSpan();

    }
    @When("I should input all the required fields")
    public void i_should_input_all_the_required_fields() {
        // Write code here that turns the phrase above into concrete actions
        ot_member_create.registerOTmember("Test","OT Member","123456");

    }

    @When("I click on the register button")
    public void i_click_on_the_register_button() {
        // Write code here that turns the phrase above into concrete actions
    ot_member_create.clickRegisterBtn();
    }

    @Then("It should creates an OT member")
    public void it_should_creates_an_ot_member() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("It creates successfully");


    }


}
