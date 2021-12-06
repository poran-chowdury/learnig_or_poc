package org.ts4u.blog.dto.request;

import lombok.Builder;
import lombok.Data;

/**
 * @author TOWFIQUL ISLAM
 * @email towfiq.106@gmail.com
 * @since 11/26/21 12:11 AM
 */

@Builder
@Data
public class LoginRequestDto {

    private String email;

    private String password;
}
