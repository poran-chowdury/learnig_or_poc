package snt.ecom.rest_assured.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author TOWFIQUL ISLAM
 * @since 25/07/2021 20:15
 */

@Getter
@AllArgsConstructor
public enum StatusCode {

    STATUS_OK(200),
    CREATED(201),
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    NOT_FOUND(404);

    private int statusCode;
}
