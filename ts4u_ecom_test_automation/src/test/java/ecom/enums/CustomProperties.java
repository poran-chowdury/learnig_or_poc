package ecom.enums;

import java.util.HashMap;

/**
 * @author TOWFIQUL ISLAM
 * @since 31/07/2021 04:55
 */

public class CustomProperties {

    public static HashMap<String, String> properties = new HashMap<>();

    public String getCustomerEmail() {
        return properties.get("customer.email");
    }

    public String getCustomerPassword() {
        return properties.get("customer.password");
    }

}
