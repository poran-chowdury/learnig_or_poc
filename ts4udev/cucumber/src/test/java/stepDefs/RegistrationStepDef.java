package stepDefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import steps.RegistrationSteps;

/**
 * @author TOWFIQUL ISLAM
 * @since 25/07/2021 19:49
 */

public class RegistrationStepDef {

    private RegistrationSteps registrationSteps = new RegistrationSteps();


    @Given("^I register FFM user \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void iRegisterFFMUser(String firstName, String lastName, String email, String password, String username) throws Throwable {
        registrationSteps.registerFFMMember(firstName, lastName, email, password, username);
    }

    @Then("I verify FFM user is created with status code {string} and {string} message")
    public void iVerifyFFMUserIsCreatedWithStatusCodeAndMessage(String statusCode, String message) {
        registrationSteps.verifyUserIsRegistered(statusCode, message);
    }
}
