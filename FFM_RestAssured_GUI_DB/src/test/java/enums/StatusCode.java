package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusCode {

    STATUS_OK(200),
    DELETED(200),
    ALREADY_EXISTS(400),
    STATUS_CREATED(201);

    private int statusCode;
}
