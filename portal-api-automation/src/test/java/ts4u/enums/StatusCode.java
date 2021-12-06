package ts4u.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusCode {

    STATUS_OK(200),
    CREATED(201),
    BAD_REQUEST(400),
    UNAUTHORIZED(401);

    private int statusCode;
}
