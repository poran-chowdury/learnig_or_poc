package ecom.providers;

import ecom.dto.admin.product.response.ProductCreateResponseDto;
import ecom.entity.Product;
import io.restassured.response.Response;

import java.util.HashMap;

/**
 * @author TOWFIQUL ISLAM
 * @since 07/08/2021 01:05
 */

public class ProductDetails {

    private static final HashMap<String, Object> productMap = new HashMap<>();

    private ProductCreateResponseDto responseDto;

    public static HashMap<String, Object> getProductMap() {
        return productMap;
    }

    private static void addValue(String key, Object value) {
        if (productMap.containsKey(key)) {
            productMap.replace(key, value);
        } else {
            productMap.put(key, value);
        }
    }

    public void setResponse(Response response) {
        responseDto = response.getBody().as(ProductCreateResponseDto.class);
        setProductMapValue(responseDto.getProduct());
    }

    private void setProductMapValue(Product product) {
        addValue("thumbnail", product.getThumbnail());
        addValue("productSlug", product.getSlug());
        addValue("productName", product.getName());
        addValue("payablePrice", product.getPrice());
        addValue("purchasedQty", 0);
        addValue("variations", product.getVariations());
        addValue("campaign", product.getCampaigns());
        addValue("productId", product.get_id());
    }

}
