package ecom.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author TOWFIQUL ISLAM
 * @since 05/08/2021 01:27
 */

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    private String _id;

    private Integer level;

    private String category;

}
