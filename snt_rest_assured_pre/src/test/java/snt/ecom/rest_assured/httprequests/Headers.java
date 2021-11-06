package snt.ecom.rest_assured.httprequests;

import snt.ecom.rest_assured.enums.HeadersEnum;

import java.util.HashMap;

/**
 * @author TOWFIQUL ISLAM
 * @since 25/07/2021 20:07
 */

public class Headers {

    private static final HashMap<String, String> headersMap = new HashMap<>();

    public static void addAHeader(String headerName, String headerValue) {
        if (headersMap.containsKey(headerName)) {
            headersMap.replace(headerName, headerValue);
        } else {
            headersMap.put(headerName, headerValue);
        }
    }

    public static HashMap<String, String> getHeaders() {
        headersMap.put(HeadersEnum.APPLICATION_JSON.getHeaderKey(), HeadersEnum.APPLICATION_JSON.getHeaderValue());
        return headersMap;
    }
}
