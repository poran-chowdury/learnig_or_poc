package snt.ecom.rest_assured.stepDefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import snt.ecom.rest_assured.service.DBService;

/**
 * @author TOWFIQUL ISLAM
 * @since 09/09/2021 02:50
 */

public class DataBaseStepDefs {

    @Given("I connect to DB")
    public void iConnectToDB() {
        DBService.connectDB();
    }

    @Then("I delete DB data")
    public void iDeleteDBData() {
        DBService.deleteQuery();
    }

    @Then("I close DB")
    public void iCloseDB() {
        DBService.closeDBConnection();
    }

}
