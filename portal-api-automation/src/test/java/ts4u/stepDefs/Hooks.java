package ts4u.stepDefs;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import ts4u.enums.CustomProperties;
import ts4u.utils.FileUtils;

import java.io.IOException;
import java.util.Properties;

public class Hooks {

    private final FileUtils fileUtils = new FileUtils();

    /***
     * This method will execute before the test suite starts and calls
     * the configureEndpoints method
     */
    @Before
    public void setup() throws IOException {
        System.out.println("setup");

        configurePropertiesFile();

        // DBService.connectDB();
    }

    /***
     * This method executes after the test suite and close the webdriver session and close the
     * db connection
     */
    @After
    public void closeUIApplication() {
        System.out.println("closeUIApplication");

//        if (DBService.dbConnection != null) {
//            DBService.closeDBConnection();
//        }
    }

    /***
     * This method reads the endpoints.properties files and load them into a hash-map
     * which is finally being used by the Endpoints class to set various endpoints
     */
    private void configurePropertiesFile() throws IOException {

        System.out.println("configurePropertiesFile");
        System.out.println("Importing properties values...");

        Properties properties = fileUtils.readPropertiesFile("custom.properties");
        CustomProperties.properties = fileUtils.loadPropertiesFileInHashMap(properties);
    }

}
