package ecom.httprequests;

import ecom.enums.CustomProperties;

import java.util.HashMap;

public class EndPoints {
    private static final HashMap<String, String> properties= CustomProperties.properties;
    public static final String baseURL= properties.get("baseURL");
    public static final String registration= baseURL+properties.get("api.registration");
    public static final String verifyOtp= baseURL+properties.get("api.verify.otp");
    public static final String customerLogin= baseURL+properties.get("api.login.customer");

    public static String dbDetails(){
        return properties.get("dbDetails");
    }
}
