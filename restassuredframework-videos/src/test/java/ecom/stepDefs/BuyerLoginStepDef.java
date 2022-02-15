package ecom.stepDefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import ecom.service.LoginService;

public class BuyerLoginStepDef {
    LoginService loginService= new LoginService();

    @Given("Login with buyer {string} and {string}")
    public void login_with_buyer_and(String email, String password) {
        // Write code here that turns the phrase above into concrete actions
       loginService.customerLogin(email, password);
    }

    @Then("Check login response for status code {string}")
    public void check_login_response_for_status_code(String statusOk) {
        // Write code here that turns the phrase above into concrete actions
        loginService.verifyLoginResponse(statusOk);

    }
}
