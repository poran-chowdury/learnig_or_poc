package stepDefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import steps.DeleteSteps;

public class DeleteStepDef {

    private DeleteSteps deleteSteps = new DeleteSteps();

    @When("I delete the FFM user")
    public void iDeleteTheFFMUser() {
        deleteSteps.deleteFFMUser("");
    }

    @And("I delete the company manager")
    public void iDeleteTheCompanyManager() {
        deleteSteps.deleteCompanyManager();
    }

    @And("I delete the company")
    public void iDeleteTheCompany() {
        deleteSteps.deleteCompany();
    }

    @Then("the user should be deleted with status code {string} and message {string}")
    public void theUserShouldBeDeletedWithStatusCodeAndMessage(String statusCode, String message) {
        deleteSteps.verifyUserIsDeleted(statusCode, message);
    }
}
