package ecom.enums;

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
    UNAUTHORIZED(401);

    private int statusCode;
}
