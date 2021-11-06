package ecom.stepDefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import ecom.service.LoginService;

/**
 * @author TOWFIQUL ISLAM
 * @since 02/08/2021 19:23
 */

public class LoginStepDef {

    LoginService loginService = new LoginService();

    @Given("Login with customer {string} and {string}")
    public void customerLoginRequest(String email, String password) {
        loginService.customerLogin(email, password);
    }

    @Given("Login a admin user using {string} and {string}")
    public void adminLoginRequest(String email, String password) {
        loginService.adminLogin(email, password);
    }

    @Then("Check login response for status code {string}")
    public void loginResponse(String statusOk) {
        loginService.verifyLoginResponse(statusOk);
    }

}
