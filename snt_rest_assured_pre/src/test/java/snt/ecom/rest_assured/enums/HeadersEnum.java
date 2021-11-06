/**
 * @author TOWFIQUL ISLAM
 * @since 25/07/2021 20:09
 */


package snt.ecom.rest_assured.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HeadersEnum {

    APPLICATION_JSON("content-type", "application/json"),
    AUTHORIZATION("bearer", "");

    private String headerKey;
    private String headerValue;
}
