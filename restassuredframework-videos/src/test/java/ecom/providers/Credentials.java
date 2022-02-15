package ecom.providers;

import ecom.enums.HeadersEnum;

import java.util.HashMap;

public class Credentials {

    private static final HashMap<String, String> credentialMap = new HashMap<>();

    //Add credentials
    public static void addCredential(String key, String value) {
        if (credentialMap.containsKey(key)) {
            credentialMap.replace(key, value);
        } else {
            credentialMap.put(key, value);
        }
    }

    //Return credentials map
    public static HashMap<String, String> getCredentials() {
        credentialMap.put(HeadersEnum.APPLICATION_JSON.getHeaderKey(), HeadersEnum.APPLICATION_JSON.getHeaderValue());
        return credentialMap;
    }
}
