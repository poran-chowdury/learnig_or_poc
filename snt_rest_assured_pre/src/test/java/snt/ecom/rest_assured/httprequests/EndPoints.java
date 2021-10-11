package snt.ecom.rest_assured.httprequests;

import snt.ecom.rest_assured.enums.CustomProperties;

import java.util.HashMap;

/**
 * @author TOWFIQUL ISLAM
 * @since 25/07/2021 20:10
 */

public class EndPoints {

    private static final HashMap<String, String> properties = CustomProperties.properties;

    public static final String baseUrl = properties.get("baseUrl");

    public static final String registration = baseUrl + properties.get("api.registration");
    public static final String verifyOtp = baseUrl + properties.get("api.verify.otp");
    public static final String customerLogin = baseUrl + properties.get("api.login.customer");

    public static String dbDetails(){
        return properties.get("dbDetails");
    }

}
