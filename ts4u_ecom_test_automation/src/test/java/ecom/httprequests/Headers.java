package ecom.httprequests;

import ecom.enums.HeadersEnum;

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

    public static void resetHeaders() {
        if (!headersMap.isEmpty()) headersMap.clear();
    }

    public static void removeHeader(String header) {
        headersMap.remove(header);
    }

    public static HashMap<String, String> getHeaders() {
        headersMap.put(HeadersEnum.APPLICATION_JSON.getHeaderKey(), HeadersEnum.APPLICATION_JSON.getHeaderValue());
//        getAuthorization();
        return headersMap;
    }

    public static HashMap<String, String> getAuthorization() {
        headersMap.put(HeadersEnum.AUTHORIZATION.getHeaderKey(), HeadersEnum.AUTHORIZATION.getHeaderValue());
        return headersMap;
    }
}
