package httprequests;

import enums.HeadersEnum;

import java.util.HashMap;

/***
 * class responsible for managing API headers
 */
public class Headers {

    private static HashMap<String, String> headersMap = new HashMap<>();

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

    public static HashMap<String, String> getHeaders(){
        headersMap.put(HeadersEnum.APPLICATION_JSON.getHeaderKey(), HeadersEnum.APPLICATION_JSON.getHeaderValue());
        return headersMap;
    }

    public static HashMap<String, String> getAuthorization(){
        headersMap.put(HeadersEnum.BEARER_TOKEN.getHeaderKey(),HeadersEnum.BEARER_TOKEN.getHeaderValue());
        return headersMap;
    }
}
