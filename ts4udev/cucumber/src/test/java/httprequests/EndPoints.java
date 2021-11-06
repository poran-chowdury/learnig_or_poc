package httprequests;

import java.util.HashMap;

/**
 * @author TOWFIQUL ISLAM
 * @since 25/07/2021 20:10
 */

public class EndPoints {

    public static HashMap<String, String> endPoints = new HashMap<>();

    public static String registerFFMEndpoint() {
        return endPoints.get("baseURI") + endPoints.get("registerFFMMember");
    }

}
