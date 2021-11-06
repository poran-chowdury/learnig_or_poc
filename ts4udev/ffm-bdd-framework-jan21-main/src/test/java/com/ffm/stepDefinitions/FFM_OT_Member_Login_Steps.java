package com.ffm.stepDefinitions;

import com.ffm.pages.FFM_OT_Member_Login_POM;
import com.ffm.utils.TestBase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FFM_OT_Member_Login_Steps extends TestBase {
    FFM_OT_Member_Login_POM login_pom;

    @Given("As an FFM OT member I should give the baseURl to navigate the login panel")
    public void as_an_ffm_ot_member_i_should_give_the_base_u_rl_to_navigate_the_login_panel() {
        // Write code here that turns the phrase above into concrete actions
        //initializeDriver();
    }

    /*
    * Regular Expressions
    * 1. \"(.*)\"
    * 2. \"([^\"]*)\"
    * 3. {string}
    * */

    @When("I will input registered {string} and {string}")
    public void i_will_input_registered_email_and_password(String email, String password) {
        // Write code here that turns the phrase above into concrete actions
        login_pom=new FFM_OT_Member_Login_POM();
        login_pom.loginOtMember(email, password);
    }

    @When("I will input registered email and password")
    public void i_will_input_registered_email_and_password() {
        // Write code here that turns the phrase above into concrete actions
        login_pom=new FFM_OT_Member_Login_POM();
        login_pom.loginOtMember(prop.getProperty("email"), prop.getProperty("password"));
    }

    @When("Click on the login button")
    public void click_on_the_login_button() {
        // Write code here that turns the phrase above into concrete actions
        login_pom.clickLoginButton();
    }

    @Then("I should be able to navigate to the FFM platform")
    public void i_should_be_able_to_navigate_to_the_ffm_platform() {
        // Write code here that turns the phrase above into concrete actions
        login_pom.loginAssert();

    }
}
