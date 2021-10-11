package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author TOWFIQUL ISLAM
 * @since 25/07/2021 20:16
 */

@Getter
@AllArgsConstructor
public enum AttachmentName {
    CREATE_MANAGER("createManager");

    private String attachmentName;
}
