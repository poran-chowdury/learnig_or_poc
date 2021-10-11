package ecom.service.admin;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import ecom.dto.admin.product.request.ProductCreateRequestDto;
import ecom.entity.*;
import ecom.enums.AttachmentName;
import ecom.enums.MongoDBCollection;
import ecom.enums.StatusCode;
import ecom.httprequests.EndPoints;
import ecom.providers.ProductDetails;
import ecom.service.BaseService;
import ecom.service.DBService;
import io.restassured.response.Response;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.mongodb.client.model.Filters.eq;

/**
 * @author TOWFIQUL ISLAM
 * @since 05/08/2021 02:14
 */

public class ProductService extends BaseService {

    protected Response response;
    protected ProductCreateRequestDto requestDto;
    private final ProductDetails productDetails = new ProductDetails();

    public void prepareProductRequestDto() {

        System.out.println("prepareProductRequestDto");

        requestDto = ProductCreateRequestDto.builder()
            .name("test")
            .categories(getCategoryList())
            .brand(getBrandId())
            .unit("pc")
            .thumbnail("https://res.cloudinary.com/shimul/image/upload/v1623172935/hBlNpKG2K-Instagram-Photo-Downloader-Ingramer%20%285%29.jpg")
            .gallery(getGalleryList())
            .price("1000")
            .discount(getDiscount())
            .tax(getTax())
            .stock("50")
            .sku("random")
            .description(StringUtils.EMPTY)
            .shipping(getShipping())
            .meta(getMeta())
            .build();
    }

    public void requestProductCreateApi() {

        System.out.println("requestProductCreateApi");

        response = requests.postRequest(
            EndPoints.createProduct,
            bodyBuilder.getProductCreateRequestBody(requestDto)
        );

        productDetails.setResponse(response);

        Assert.assertEquals(StatusCode.CREATED.getStatusCode(), response.getStatusCode());
        validateAndAttachResponse(AttachmentName.CREATE_PRODUCT, response);
    }

    public void verifyCreateProductResponse(String statusCode) {

        System.out.println("verifyCreateProductResponse");

        response
            .then()
            .assertThat()
            .statusCode(Integer.parseInt(statusCode));
    }

    public String getProductId() {
        DBService.getDBCollectionData(MongoDBCollection.PRODUCTS);
        MongoCollection<Document> collection = DBService.collection;
        FindIterable<Document> iterable = collection.find();
        return Objects.requireNonNull(iterable.first()).get("_id").toString();
    }

    public String getProductSlug() {
        DBService.getDBCollectionData(MongoDBCollection.PRODUCTS);
        MongoCollection<Document> collection = DBService.collection;
        FindIterable<Document> iterable = collection.find(eq("_id", new ObjectId(getProductId())));
        return Objects.requireNonNull(iterable.first()).get("slug").toString();
    }

    public String getProductName() {
        DBService.getDBCollectionData(MongoDBCollection.PRODUCTS);
        MongoCollection<Document> collection = DBService.collection;
        FindIterable<Document> iterable = collection.find(eq("_id", new ObjectId(getProductId())));
        return Objects.requireNonNull(iterable.first()).get("name").toString();
    }

    public void verifyCreatedProductInDB() {

        System.out.println("verifyCreatedProductInDB");

        boolean isDataExist = DBService.isDBDataExist(
            MongoDBCollection.PRODUCTS,
            eq("_id", new ObjectId(ProductDetails.getProductMap().get("productId").toString()))
        );

        Assert.assertTrue(isDataExist);
    }

    private Tax getTax() {
        return Tax.builder()
            .taxType("flat")
            .value("10")
            .build();
    }

    private Discount getDiscount() {
        return Discount.builder()
            .discountType("percent")
            .value("10")
            .build();
    }

    private Shipping getShipping() {
        return Shipping.builder()
            .isFree(true)
            .cost("5")
            .build();
    }

    private Meta getMeta() {
        return Meta.builder()
            .title("meta tt")
            .description("meta desc")
            .image("https://res.cloudinary.com/shimul/image/upload/v1623167441/nm_0M62mL-61211157_901830496825043_5007787223332295180_n.jpg")
            .build();
    }

    private List<String> getGalleryList() {
        List<String> gallery = new ArrayList<>();

        gallery.add("https://res.cloudinary.com/shimul/image/upload/v1623169085/nENV8qaG2-Instagram-Photo-Downloader-Ingramer.jpg");
        gallery.add("https://res.cloudinary.com/shimul/image/upload/v1623167441/nm_0M62mL-61211157_901830496825043_5007787223332295180_n.jpg");

        return gallery;
    }

    private List<Category> getCategoryList() {
        List<Category> categories = new ArrayList<>();

        categories.add(
            Category.builder()
                .level(1)
                .category("608c787927be2cba1ca96f76")
                .build()
        );

        categories.add(
            Category.builder()
                .level(2)
                .category("608c793f27be2cba1ca96f82")
                .build()
        );

        categories.add(
            Category.builder()
                .level(3)
                .category("608c798f27be2cba1ca96f87")
                .build()
        );

        return categories;
    }

    private String getBrandId() {

        return "608c7ae427be2cba1ca96f9b";
    }

}
