package stepDefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import steps.LoginSteps;

import java.util.List;
import java.util.Map;

public class AuthenticationStepDef {

    private LoginSteps loginSteps = new LoginSteps();

    @When("I login with following details")
    public void iLoginWithFollowingDetails(DataTable dataTable) {
        List<Map<String, String>> dataTableMapList = dataTable.asMaps(String.class, String.class);
        String email = dataTableMapList.get(0).get("email");
        String password = dataTableMapList.get(0).get("password");
        loginSteps.login(email, password);
    }

    @And("I authenticate dashboard user")
    public void iAuthenticateDashboardUser() {
        loginSteps.authorizeUser();
    }

    @And("I generate refresh token")
    public void iGenerateRefreshToken() {
        loginSteps.generateRefreshToken();
    }

    @Then("I logout")
    public void iLogout() {
        loginSteps.logout();
    }

    @Then("I should be logged in with status code {string} and {string} message")
    public void iShouldBeLoggedInWithStatusCodeAndMessage(String statusCode, String message) {
        loginSteps.verifyUserIsLoggedIn(statusCode, message);
    }

    @Then("I should be able to logout")
    public void iShouldBeAbleToLogout() {
        loginSteps.verifyUserIsLoggedOut();
    }

    @And("I login manager with following details")
    public void iLoginManagerWithFollowingDetails(DataTable dataTable) {
        List<Map<String, String>> dataTableMapList = dataTable.asMaps(String.class, String.class);
        String email = dataTableMapList.get(0).get("email");
        String password = dataTableMapList.get(0).get("password");
        loginSteps.loginManager(email, password);
    }
}
