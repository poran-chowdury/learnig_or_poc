package org.ts4u.blog.utils;

/**
 * @author TOWFIQUL ISLAM
 * @email towfiq.106@gmail.com
 * @since 11/25/21 11:37 PM
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class FileUtils {

    /**
     * @param fileName
     * @return
     * @throws IOException
     */
    public Properties readPropertiesFile(String fileName) throws IOException {
        Properties prop = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        if (inputStream != null) {
            prop.load(inputStream);
        } else {
            throw new FileNotFoundException("property file '" + fileName + "' not found in the classpath");
        }
        return prop;
    }

    /***
     * loads properties into hash-map
     */
    public HashMap<String, String> loadPropertiesFileInHashMap(Properties properties) {
        HashMap<String, String> propertiesMap = new HashMap<>();
        for (String key : properties.stringPropertyNames()) {
            String value = properties.getProperty(key);
            propertiesMap.put(key, value);
        }
        return propertiesMap;
    }
}
