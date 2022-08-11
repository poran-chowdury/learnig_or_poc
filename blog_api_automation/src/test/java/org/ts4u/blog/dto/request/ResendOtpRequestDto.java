package org.ts4u.blog.dto.request;

import lombok.Builder;
import lombok.Data;

/**
 * @author TOWFIQUL ISLAM
 * @email towfiq.106@gmail.com
 * @since 11/26/21 1:10 AM
 */

@Builder
@Data
public class ResendOtpRequestDto {

    private String email;

}
