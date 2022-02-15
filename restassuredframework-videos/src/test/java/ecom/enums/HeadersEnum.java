package ecom.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

public enum HeadersEnum {

    APPLICATION_JSON("content-type", "application/json"),
    AUTHORIZATION("bearer","");
    private String headerKey;
    private String headerValue;
}
