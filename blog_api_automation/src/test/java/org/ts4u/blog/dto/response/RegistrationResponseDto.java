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
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationResponseDto {

    private Boolean isOtpSend;

    private String email;

    private String message;

}
