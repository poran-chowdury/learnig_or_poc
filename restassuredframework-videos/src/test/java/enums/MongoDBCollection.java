package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MongoDBCollection {
    USERS("users");
    private String collectionName;
}
