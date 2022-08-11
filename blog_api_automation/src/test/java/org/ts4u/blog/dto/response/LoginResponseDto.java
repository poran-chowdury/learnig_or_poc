package org.ts4u.blog.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author TOWFIQUL ISLAM
 * @email towfiq.106@gmail.com
 * @since 11/25/21 11:55 PM
 */

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginResponseDto {

    private String token;

    private String user;

    private String profileimg;

    private String success;

    private String message;

}
