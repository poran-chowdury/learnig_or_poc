package ts4u.enums;

import java.util.HashMap;

public class CustomProperties {

    public static HashMap<String, String> properties = new HashMap<>();

    public String getCandidateEmail() {
        return properties.get("candidate.email");
    }

    public String getCandidatePassword() {
        return properties.get("candidate.password");
    }

}
