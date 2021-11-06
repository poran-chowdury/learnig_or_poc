package utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

/***
 * All operations related to json will be the part of this class
 */
public class JsonUtils {

    /***
     * reads json file as a string
     * @param fileName
     * @return
     */
    public String readJSONFileAsString(String fileName){
        JSONParser jsonParser = new JSONParser();
        Object obj;
        JSONObject testJSON = new JSONObject();
        try (FileReader reader = new FileReader(fileName)) {
            obj = jsonParser.parse(reader);
            testJSON = (JSONObject)obj;
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return testJSON.toString();
    }

    /***
     * write json object in a file
     * @param jsonObject
     * @param fileName
     */
    public void writeJSONFile(JSONObject jsonObject, String fileName){
        try (FileWriter file = new FileWriter(fileName)) {
            file.write(jsonObject.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /***
     * delete a file
     * @param fileName
     */
    public void deleteFile(String fileName){
        File file = new File(fileName);
        file.delete();
    }
}
