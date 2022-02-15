package ecom.stepDefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ecom.providers.Credentials;
import ecom.service.DBService;
import ecom.service.RegistrationService;

import java.util.HashMap;

public class ValidateBuyerRegistrationStepDef {

    RegistrationService registrationService= new RegistrationService();
    @Given("I connect to the DB")
    public void i_connect_to_the_DB() {
        // Write code here that turns the phrase above into concrete actions
        DBService.connectDB();
    }

    @When("Validate with collection name")
    public void validate_with_collection_name() {
        // Write code here that turns the phrase above into concrete actions
        HashMap<String, String> credentialsMap = Credentials.getCredentials();
        String email = credentialsMap.get("email");
        System.out.print("email in map: ");
        System.out.println(email);
        registrationService.checkForUserData(email);
    }

    @Then("close the db")
    public void close_the_db() {
        // Write code here that turns the phrase above into concrete actions
        DBService.closeDBConnection();
    }
}
