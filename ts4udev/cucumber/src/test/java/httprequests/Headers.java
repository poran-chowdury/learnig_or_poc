package httprequests;

import enums.HeadersEnum;

import java.util.HashMap;

/**
 * @author TOWFIQUL ISLAM
 * @since 25/07/2021 20:07
 */

public class Headers {

    private static HashMap<String, String> headersMap = new HashMap<>();

    public static HashMap<String, String> getHeaders() {
        headersMap.put(HeadersEnum.APPLICATION_JSON.getHeaderKey(), HeadersEnum.APPLICATION_JSON.getHeaderValue());
        return headersMap;
    }
}
