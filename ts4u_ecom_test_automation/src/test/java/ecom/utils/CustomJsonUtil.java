package ecom.utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/**
 * @author TOWFIQUL ISLAM
 * @since 06/08/2021 00:03
 */

public class CustomJsonUtil {

    private static String getValueByKey(String key, Response response) {
        JsonPath jsonPathEvaluator = response.jsonPath();
        return jsonPathEvaluator.get(key);
    }
}
