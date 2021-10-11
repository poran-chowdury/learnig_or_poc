package stepDefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import steps.RegistrationSteps;

import java.util.List;
import java.util.Map;

public class RegistrationStepDef {

    private RegistrationSteps registrationSteps = new RegistrationSteps();


    @Given("^I register FFM user \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void iRegisterFFMUser(String firstName, String lastName, String email, String password, String username) throws Throwable {
        registrationSteps.registerFFMMember(firstName, lastName, email, password, username);
    }

    @Given("I register a company with following details")
    public void iRegisterACompanyWithFollowingDetails(DataTable dataTable) {
        List<Map<String, String>> dataTableMapList = dataTable.asMaps(String.class, String.class);
        String name = dataTableMapList.get(0).get("name");
        String address = dataTableMapList.get(0).get("address");
        String tin = dataTableMapList.get(0).get("tin");
        registrationSteps.registerCompany(name, address, tin);
    }

    @Given("I register a company with {string} {string} {string}")
    public void iRegisterACompanyWith(String name, String address, String tin) {
        registrationSteps.registerCompany(name, address, tin);
    }

    @Given("I register FFM user with following details")
    public void iRegisterFFMUserWithFollowingDetails(DataTable dataTable) {
        List<Map<String, String>> dataTableMapList = dataTable.asMaps(String.class, String.class);
        String firstName = dataTableMapList.get(0).get("firstname");
        String lastName = dataTableMapList.get(0).get("lastname");
        String email = dataTableMapList.get(0).get("email");
        String password = dataTableMapList.get(0).get("password");
        String username = dataTableMapList.get(0).get("username");
        registrationSteps.registerFFMMember(firstName, lastName, email, password, username);
    }

    @When("I register a company manager with {string} {string} {string} {string} {string} {string}")
    public void iRegisterACompanyManagerWith(String firstName, String lastName, String email, String password, String username, String memberType) {
        String company_id = registrationSteps.getCompanyId();
        registrationSteps.registerCompanyManager(firstName, lastName, email, password, username, company_id, memberType);
    }

    @When("I register a company manager with following details")
    public void iRegisterACompanyManagerWithFollowingDetails(DataTable dataTable) {
        List<Map<String, String>> dataTableMapList = dataTable.asMaps(String.class, String.class);
        String firstName = dataTableMapList.get(0).get("firstName");
        String lastName = dataTableMapList.get(0).get("lastName");
        String email = dataTableMapList.get(0).get("email");
        String password = dataTableMapList.get(0).get("password");
        String username = dataTableMapList.get(0).get("username");
        String member_type = dataTableMapList.get(0).get("member_type");
        String company_id= registrationSteps.getCompanyId();
        registrationSteps.registerCompanyManager(firstName, lastName, email, password, username, company_id, member_type);
    }

    @Then("I verify FFM user is created with status code {string} and {string} message")
    public void iVerifyFFMUserIsCreatedWithStatusCodeAndMessage(String statusCode, String message) {
        registrationSteps.verifyUserIsRegistered(statusCode, message);
    }

    @Then("I verify company is created with status code {string} and {string} message")
    public void iVerifyCompanyIsCreatedWithStatusCodeAndMessage(String statusCode, String message) {
        registrationSteps.verifyCompanyIsRegistered(statusCode, message);
    }

    @Then("I verify company manager is registered with status code {string} and {string} message")
    public void iVerifyCompanyManagerIsRegisteredWithStatusCodeAndMessage(String statusCode, String message) {
        registrationSteps.verifyCompanyManagerIsRegistered(statusCode, message);
    }
}
