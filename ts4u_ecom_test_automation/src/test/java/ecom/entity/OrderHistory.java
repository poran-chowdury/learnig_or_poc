package ecom.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author TOWFIQUL ISLAM
 * @since 06/08/2021 23:28
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderHistory {

    private String type;

    private String note;

    private String _id;

    private String date;

}
