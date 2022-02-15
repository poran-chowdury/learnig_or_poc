package utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class FileUtils {

    //Read the properties file
    public Properties readPropertiesFile(String fileName) throws IOException {
        Properties prop= new Properties();
        InputStream inputStream= getClass().getClassLoader().getResourceAsStream(fileName);
        if (inputStream!= null){
            prop.load(inputStream);
        }else{
            throw new FileNotFoundException("Property file: "+ fileName +"not found in the classpath");
        }
    return prop;}

    //Load properties file
    public HashMap<String, String> loadPropertiesFileInHashMap(Properties properties){
        HashMap<String, String> propertiesMap= new HashMap<>();
        for (String key: properties.stringPropertyNames()){
            String value= properties.getProperty(key);
            propertiesMap.put(key,value);
        }
    return propertiesMap;}

}
