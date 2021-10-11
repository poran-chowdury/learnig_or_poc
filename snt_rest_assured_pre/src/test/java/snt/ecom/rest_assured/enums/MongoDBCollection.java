/**
 * @author TOWFIQUL ISLAM
 * @since 14/09/2021 01:00
 */


package snt.ecom.rest_assured.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MongoDBCollection {

    USERS("users");

    private String collectionName;

}
