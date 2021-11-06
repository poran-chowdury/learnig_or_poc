package ecom.dto.admin.product.response;

import ecom.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author TOWFIQUL ISLAM
 * @since 06/08/2021 21:57
 */

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateResponseDto {

    private Boolean success;

    private Product product;

}
