package org.ts4u.blog.httprequests;

import org.ts4u.blog.enums.HeadersEnum;

import java.util.HashMap;

/**
 * @author TOWFIQUL ISLAM
 * @email towfiq.106@gmail.com
 * @since 11/25/21 11:53 PM
 */

public class Headers {

    private static final HashMap<String, String> headersMap = new HashMap<>();

    /**
     * @param headerName
     * @param headerValue
     * Add a new header
     */
    public static void addAHeader(String headerName, String headerValue) {
        if (headersMap.containsKey(headerName)) {
            headersMap.replace(headerName, headerValue);
        } else {
            headersMap.put(headerName, headerValue);
        }
    }

    /**
     * Get Header
     * @return Header as HashMap
     */
    public static HashMap<String, String> getHeaders() {
        headersMap.put(HeadersEnum.APPLICATION_JSON.getHeaderKey(), HeadersEnum.APPLICATION_JSON.getHeaderValue());
        return headersMap;
    }
}
