package ts4u.httprequests;

import ts4u.enums.CustomProperties;

import java.util.HashMap;

public class EndPoints {

    private static final HashMap<String, String> properties = CustomProperties.properties;

    public static final String baseUrl = properties.get("baseUrl");
    public static final String customerLogin = baseUrl + properties.get("api.login.candidate");
    public static final String registration = baseUrl + properties.get("api.registration.candidate");
    public static final String verifyOtp = baseUrl + properties.get("api.verify.otp");
    public static final String resendOtp = baseUrl + properties.get("api.resend.otp");
}
