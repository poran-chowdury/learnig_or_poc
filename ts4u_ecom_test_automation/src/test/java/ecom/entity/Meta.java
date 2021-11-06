package ecom.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author TOWFIQUL ISLAM
 * @since 05/08/2021 01:34
 */


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Meta {

    private String title;

    private String description;

    private String image;

}
