package snt.ecom.rest_assured.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author TOWFIQUL ISLAM
 * @since 25/07/2021 20:16
 */

@Getter
@AllArgsConstructor
public enum AttachmentName {

    CREATE_CUSTOMER("createCustomer"),
    VERIFY_OTP("verifyOtp"),
    LOGIN("login");

    private String attachmentName;
}
