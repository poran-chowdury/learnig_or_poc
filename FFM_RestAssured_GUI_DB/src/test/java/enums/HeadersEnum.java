package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HeadersEnum {

    APPLICATION_JSON("content-type", "application/json"),
    BEARER_TOKEN("bearer", "aeyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1OTY3Mzg3NTUsImlhdCI6MTU5NjczNTE1NSwic3ViIjozNzcsInVzZXJfdHlwZSI6IkRhc2hib2FyZCIsIm5hbWUiOiJQcmFudG8gTWF6dW1kZXIiLCJlbWFpbCI6InByYW50by5tYXp1bWRlcjRAZW1haWwuY29tIiwibWVtYmVyX3R5cGUiOjIsImNvbXBhbnlfaWQiOjE4OSwiZGVzaWduYXRpb24iOm51bGwsImRlcGFydG1lbnQiOm51bGx9.C-hiexJlFjxfbMj5euWpUINesS4r13yLWsVLsB9XV-Y");

    private String headerKey;
    private String headerValue;
}
