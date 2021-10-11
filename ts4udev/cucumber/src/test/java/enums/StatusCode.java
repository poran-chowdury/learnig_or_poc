package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author TOWFIQUL ISLAM
 * @since 25/07/2021 20:15
 */

@Getter
@AllArgsConstructor
public enum StatusCode {

    ALREADY_EXISTS(400),
    STATUS_CREATED(201);

    private int statusCode;
}
