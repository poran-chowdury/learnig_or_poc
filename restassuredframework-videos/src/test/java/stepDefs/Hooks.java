package stepDefs;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import enums.CustomProperties;
import service.DBService;
import utils.FileUtils;

import java.io.IOException;
import java.util.Properties;

public class Hooks {
    private final FileUtils fileUtils = new FileUtils();

    @Before
    public void setup() throws IOException {
        System.out.println("Setup");
        configurePropertiesFile();
        DBService.connectDB();

    }

    private void configurePropertiesFile() throws IOException {
        Properties properties = fileUtils.readPropertiesFile("custom.properties");
        CustomProperties.properties=fileUtils.loadPropertiesFileInHashMap(properties);
    }

    @After
    public void tearDown(){
        if (DBService.dbConnection != null){
            DBService.closeDBConnection();
        }
    }

}
