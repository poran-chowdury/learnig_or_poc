package ecom.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author TOWFIQUL ISLAM
 * @since 06/08/2021 22:51
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Variations {

    private String ram;

    private String rom;

}
