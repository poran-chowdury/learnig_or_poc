package org.ts4u.blog.stepDefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.ts4u.blog.providers.CustomProperties;
import org.ts4u.blog.utils.FileUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * @author TOWFIQUL ISLAM
 * @email towfiq.106@gmail.com
 * @since 11/25/21 11:37 PM
 */

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
    }

    /***
     * This method executes after the test suite and close the webdriver session and close the
     * db connection
     */
    @After
    public void tearDownApplication() {
        System.out.println("tearDownApplication");
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
