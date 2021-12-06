package ts4u.dto.admin.product.request;

import ts4u.entity.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ProductCreateRequestDto {

    private String name;

    private List<Category> categories;

    private String brand;

    private String unit;

    private String thumbnail;

    private List<String> gallery;

    private String price;

    private Discount discount;

    private Tax tax;

    private String stock;

    private String sku;

    private String description;

    private Shipping shipping;

    private Meta meta;

}
