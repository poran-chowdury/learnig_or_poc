package ecom.httprequests;

import ecom.enums.CustomProperties;

import java.util.HashMap;

/**
 * @author TOWFIQUL ISLAM
 * @since 25/07/2021 20:10
 */

public class EndPoints {

    private static final HashMap<String, String> properties = CustomProperties.properties;

    public static final String baseUrl = properties.get("baseUrl");

    public static final String adminLogin = baseUrl + properties.get("api.login.admin");

    public static final String createProduct = baseUrl + properties.get("api.product.create");

    public static final String registration = baseUrl + properties.get("api.registration");
    public static final String verifyOtp = baseUrl + properties.get("api.verify.otp");
    public static final String resendOtp = baseUrl + properties.get("api.resend.otp");
    public static final String customerLogin = baseUrl + properties.get("api.login.customer");
    public static final String verifyToken = baseUrl + properties.get("api.verify.token");

    public static final String createAddress = baseUrl + properties.get("api.address.create");
    public static final String getAddress = baseUrl + properties.get("api.address.getaddress");

    public static final String createOrder = baseUrl + properties.get("api.order.create");

    public static String dbDetails(){
        return properties.get("dbDetails");
    }

}
