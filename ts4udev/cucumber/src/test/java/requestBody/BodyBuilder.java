package requestBody;

import com.google.gson.Gson;
import payload.RegisterFMMPayload;

/**
 * @author TOWFIQUL ISLAM
 * @since 25/07/2021 20:12
 */

public class BodyBuilder {

    public String getRegisterFFMRequestBody(String firstName, String lastName, String email, String password, String username) {
        return new Gson().toJson(
            RegisterFMMPayload
                .builder()
                .firstname(firstName)
                .lastname(lastName)
                .email(email)
                .username(username)
                .password(password)
                .build()
        );
    }
}
