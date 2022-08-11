package ts4u.stepDefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import ts4u.services.LoginService;

public class LoginStepDef {
    LoginService loginService = new LoginService();

    @Given("Login with candidate {string} and {string}")
    public void login_with_candidate_and(String email, String password) {
        loginService.customerLogin(email, password);
    }

    @Then("Check login response for status code {string} and success is {string}")
    public void check_login_response_for_status_code_and_success_is(String statusCode, String success) {
        loginService.verifyLoginResponse(statusCode, Boolean.parseBoolean(success));
    }
}