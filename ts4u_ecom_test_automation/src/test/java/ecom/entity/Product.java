package ecom.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author TOWFIQUL ISLAM
 * @since 06/08/2021 22:00
 */

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private List<String> tags;

    private List<String> gallery;

    private List<String> attributes;

    private List<String> variations;

    private String productType;

    private Boolean isActive;

    private Boolean isFeatured;

    private Integer sales;

    private String _id;

    private String name;

    private String slug;

    private List<Category> categories;

    private String brand;

    private String thumbnail;

    private Double price;

    private Discount discount;

    private Tax tax;

    private String sku;

    private String stock;

    private String description;

    private Shipping shipping;

    private Meta meta;

    private String unit;

    private List<String> campaigns;

    private String createdBy;

    private String createdAt;

    private String updatedAt;

    private Double __v;

}
