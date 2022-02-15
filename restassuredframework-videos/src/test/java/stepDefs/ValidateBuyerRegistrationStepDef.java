package stepDefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import service.DBService;
import service.RegistrationService;

public class ValidateBuyerRegistrationStepDef {

    RegistrationService registrationService= new RegistrationService();
    @Given("I connect to the DB")
    public void i_connect_to_the_DB() {
        // Write code here that turns the phrase above into concrete actions
        DBService.connectDB();
    }

    @When("Validate with collection name {string}")
    public void validate_with_collection_name(String email) {
        // Write code here that turns the phrase above into concrete actions
        registrationService.checkForUserData(email);
    }

    @Then("close the db")
    public void close_the_db() {
        // Write code here that turns the phrase above into concrete actions
        DBService.closeDBConnection();
    }
}
