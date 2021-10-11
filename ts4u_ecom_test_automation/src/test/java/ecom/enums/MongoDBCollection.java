/**
 * @author TOWFIQUL ISLAM
 * @since 14/09/2021 01:00
 */


package ecom.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MongoDBCollection {

    PRODUCTS("products"),
    ORDERS("orders"),
    USERS("users");

    private String collectionName;

}
