package org.ts4u.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author TOWFIQUL ISLAM
 * @email towfiq.106@gmail.com
 * @since 11/26/21 12:03 AM
 */

@Getter
@AllArgsConstructor
public enum StatusCode {

    STATUS_OK(200),
    CREATED(201),
    ;

    private int statusCode;
}
