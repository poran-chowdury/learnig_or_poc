package stepDefs.ui;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import io.cucumber.datatable.DataTable;
import steps.BaseSteps;
import ui.driver.DriverFactory;
import ui.pageobjeccts.DashboardPage;
import ui.pageobjeccts.LoginPage;

import java.util.List;
import java.util.Map;

public class UiStepDef {

    LoginPage loginPage = new LoginPage();
    DashboardPage dashboardPage;

    @And("I login the UI application with following details")
    public void iLoginTheUIApplicationWithFollowingDetails(DataTable dataTable) {
        List<Map<String, String>> dataTableMapList = dataTable.asMaps(String.class, String.class);
        String email = dataTableMapList.get(0).get("email");
        String password = dataTableMapList.get(0).get("password");
        dashboardPage = loginPage.login(email, password);
    }

    @And("I launch the UI application")
    public void iLaunchTheUIApplication() { DriverFactory.setDriver(); }

    @And("I delete the company with company name {string}")
    public void iDeleteTheCompanyWithCompanyName(String companyName) throws InterruptedException {
        //dashboardPage.deleteCompany();
    }

    @And("I delete all companies from UI")
    public void iDeleteAllCompaniesFromUI() throws InterruptedException { dashboardPage.deleteAllCompanies(); }

    @And("I delete all company admins from UI")
    public void iDeleteAllCompanyAdminsFromUI() { dashboardPage.deleteAllCompanyAdmins(); }

    @And("I delete all super admins from UI")
    public void iDeleteAllSuperAdminsFromUI() { dashboardPage.deleteAllSuperAdmins(); }

    @And("I delete all services")
    public void iDeleteAllServicesFromUI() { dashboardPage.deleteAllServices(); }

    @And("I logout the UI application")
    public void iLogoutTheUIApplication() throws InterruptedException { dashboardPage.logout(); }

    @Then("I verify all users are deleted from UI")
    public void iVerifyAllUsersAreDeletedFromUI() { dashboardPage.verifyAllRecordsHasBeenDeleted(); }

    @And("I delete ffm user with email {string} from UI")
    public void iDeleteFfmUserWithEmailFromUI(String email) {
        dashboardPage.deleteSuperAdmin(BaseSteps.ffm_email);
    }

    @And("I verify ffm user with email {string} is deleted from UI")
    public void iVerifyFfmUserWithEmailIsDeletedFromUI(String email) {
        dashboardPage.verifyUserHasBeenDeleted(BaseSteps.ffm_email);
    }

    @And("I delete company with name {string} from UI")
    public void iDeleteCompanyWithNameFromUI(String name) {
        dashboardPage.deleteCompany(BaseSteps.company_name);
    }

    @And("I verify company with name {string} is deleted from UI")
    public void iVerifyCompanyWithNameIsDeletedFromUI(String name) {
        dashboardPage.verifyCompanyHasBeenDeleted(BaseSteps.company_name);
    }

    @And("I delete company admin with email {string} from UI")
    public void iDeleteCompanyAdminWithEmailFromUI(String email) {
        dashboardPage.deleteCompanyManager(BaseSteps.manager_email);
    }

    @And("I verify company admin with email {string} is deleted from UI")
    public void iVerifyCompanyAdminWithEmailIsDeletedFromUI(String email) {
        dashboardPage.verifyCompanyAdminBeenDeleted(BaseSteps.manager_email);
    }

    @Then("I verify all companies are deleted from UI")
    public void iVerifyAllCompaniesAreDeletedFromUI() {
        dashboardPage.verifyAllRecordsHasBeenDeleted();
    }

    @Then("I verify all super admins are deleted from UI")
    public void iVerifyAllSuperAdminsAreDeletedFromUI() {
        dashboardPage.verifyAllRecordsHasBeenDeleted();
    }

    @Then("I verify all company admins are deleted from UI")
    public void iVerifyAllCompanyAdminsAreDeletedFromUI() {
        dashboardPage.verifyAllRecordsHasBeenDeleted();
    }
}
