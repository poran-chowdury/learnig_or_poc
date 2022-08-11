package ts4u.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Items {

    private String thumbnail;

    private String productSlug;

    private String productName;

    private String payablePrice;

    private Integer purchasedQty;

    private Object variations;

    private String campaign;

    private String productId;

    private String _id;

}
