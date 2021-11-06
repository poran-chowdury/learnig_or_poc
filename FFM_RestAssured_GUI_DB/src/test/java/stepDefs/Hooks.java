package stepDefs;

import cucumber.api.java.After;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import httprequests.EndPoints;
import steps.DBSteps;
import ui.driver.DriverFactory;
import utils.FileUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class Hooks {

    private FileUtils fileUtils = new FileUtils();
    /***
     * This method will execute before the test suite starts and calls
     * the configureEndpoints method
     * @throws IOException
     */
    @Before
    public void setup() throws IOException {
        System.out.println("Inside hooks");
        configureEndpoints();
    }

    /***
     * This method reads the endpoints.properties files and load them into a hash-map
     * which is finally being used by the Endpoints class to set various endpoints
     * @throws IOException
     */
    private void configureEndpoints() throws IOException {
        Properties properties = fileUtils.readPropertiesFile("endpoints.properties");
        EndPoints.endPoints = fileUtils.loadPropertiesFileInHashMap(properties);
    }

    /***
     * This method takes the UI screeshot after each UI step
     *
     */
    @AfterStep
    public void takeScreenshot(){
        try {
            if (DriverFactory.getDriver() != null) {
                DriverFactory.addScreenshotAllure();
            }
        } catch (Exception e){

        }
    }

    /***
     * This method executes after the test suite and close the webdriver session and close the
     * db connection
     */
    @After
    public void closeUIApplication() throws SQLException {
        if(DriverFactory.getDriver()!=null){
            DriverFactory.getDriver().quit();
        }
        if(DBSteps.dbConnection!=null){
            DBSteps.closeDBConnection();
        }
    }
}
