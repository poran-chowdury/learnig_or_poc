package org.ts4u.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author TOWFIQUL ISLAM
 * @email towfiq.106@gmail.com
 * @since 11/26/21 12:36 AM
 */

@Getter
@AllArgsConstructor
public enum HeadersEnum {

    APPLICATION_JSON("content-type", "application/json"),
    AUTHORIZATION("Bearer", "");

    private String headerKey;
    private String headerValue;
}
