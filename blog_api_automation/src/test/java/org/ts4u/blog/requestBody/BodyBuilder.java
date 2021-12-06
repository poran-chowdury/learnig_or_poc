package org.ts4u.blog.requestBody;


import com.google.gson.Gson;
import org.ts4u.blog.dto.request.*;

/**
 * @author TOWFIQUL ISLAM
 * @email towfiq.106@gmail.com
 * @since 11/25/21 11:53 PM
 */

public class BodyBuilder {

    /**
     * @param first_name
     * @param last_name
     * @param email
     * @param password
     * @return
     */
    public String getRegistrationRequestBody(String first_name, String last_name, String email, String password) {
        return new Gson().toJson(
                RegistrationRequestDto.builder()
                        .first(first_name)
                        .last(last_name)
                        .email(email)
                        .password(password)
                        .confirm(password)
                        .build()
        );
    }

    /**
     * @param email
     * @param password
     * @return
     */
    public String getLoginRequestBody(String email, String password) {
        return new Gson().toJson(
                LoginRequestDto.builder()
                        .email(email)
                        .password(password)
                        .build()
        );
    }

    /**
     * @param email
     * @return
     */
    public String getResendOtpBody(String email) {
        return new Gson().toJson(
                ResendOtpRequestDto.builder()
                        .email(email)
                        .build()
        );
    }

    /**
     * @param postId
     * @return
     */
    public String postSharePostRequestBody(String postId) {
        return new Gson().toJson(
                SharePostRequestDto.builder()
                        .postId(postId)
                        .build()
        );
    }

    /**
     * @param name
     * @param description
     * @return
     */
    public String postCreateBlog(String name, String description) {
        return new Gson().toJson(
                CreateBlogRequestDto.builder()
                        .name(name)
                        .description(description)
                        .build()
        );
    }
}
