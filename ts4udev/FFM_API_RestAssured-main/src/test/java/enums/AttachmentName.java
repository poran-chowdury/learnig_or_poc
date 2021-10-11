package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AttachmentName {
    CREATE_FFM_USER("createFFMUser"),
    DELETE_FFM_USER("deleteFFMUser"),
    CREATE_MANAGER("createManager"),
    DELETE_MANAGER("deleteManager"),
    CREATE_COMPANY("createCompany"),
    DELETE_COMPANY("deleteCompany"),
    AUTHORIZE_USER("authorizeUser"),
    LOGIN("login"),
    LOGOUT("logout"),
    REFRESH_TOKEN("refreshToken");

    private String attachmentName;
}
