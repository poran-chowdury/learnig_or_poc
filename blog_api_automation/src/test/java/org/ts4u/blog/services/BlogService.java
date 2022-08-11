package org.ts4u.blog.services;

import io.restassured.response.Response;
import org.junit.Assert;
import org.ts4u.blog.enums.AttachmentName;
import org.ts4u.blog.enums.StatusCode;
import org.ts4u.blog.httprequests.EndPoints;

import static org.hamcrest.CoreMatchers.equalTo;

/**
 * @author TOWFIQUL ISLAM
 * @email towfiq.106@gmail.com
 * @since 11/28/21 12:43 AM
 */

public class BlogService extends BaseService {

    protected Response response;

    /**
     * This method calls Trending Blog API
     * @method GET
     */
    public void getTrendingBlog() {
        System.out.println("getTrendingBlog");

        response = requests.getRequest(EndPoints.trendingBlog, "");

        System.out.println("Response :: " + response.asString());
        System.out.println("StatusCode :: " + response.getStatusCode());

        Assert.assertEquals(StatusCode.STATUS_OK.getStatusCode(), response.getStatusCode());

        validateAndAttachResponse(AttachmentName.TRENDING_BLOG, response);
    }


    /**
     * This method calls Create Blog API
     * @method POST
     * @param name
     * @param description
     */
    public void postCreateBlog(String name, String description) {
        System.out.println("getTrendingBlog");

        response = requests.postRequest(
                EndPoints.createBlog,
                bodyBuilder.postCreateBlog(name, description)
        );

        System.out.println("Response :: " + response.asString());
        System.out.println("StatusCode :: " + response.getStatusCode());

        Assert.assertEquals(StatusCode.CREATED.getStatusCode(), response.getStatusCode());

        validateAndAttachResponse(AttachmentName.CREATE_BLOG, response);
    }


    /**
     * This method verifies the Created Blog Response
     * @param statusCode
     * @param success
     */
    public void verifyCreatedBlogResponse(Integer statusCode, Boolean success) {
        System.out.println("verifyCreatedBlogResponse");

        response
                .then()
                .assertThat()
                .statusCode(statusCode)
                .body("success", equalTo(success));
    }

    /**
     * This method calls My Blog API
     * @method GET
     */
    public void getMyBlog() {
        System.out.println("getMyBlog");

        response = requests.getRequest(EndPoints.myBlog, "");

        System.out.println("Response :: " + response.asString());
        System.out.println("StatusCode :: " + response.getStatusCode());

        Assert.assertEquals(StatusCode.STATUS_OK.getStatusCode(), response.getStatusCode());

        validateAndAttachResponse(AttachmentName.MY_BLOG, response);
    }

    /**
     * This method calls All Blog API
     * @method GET
     */
    public void getAllBlogs() {
        System.out.println("getAllBlogs");

        response = requests.getRequest(EndPoints.getBlogs, "");

        System.out.println("Response :: " + response.asString());
        System.out.println("StatusCode :: " + response.getStatusCode());

        Assert.assertEquals(StatusCode.STATUS_OK.getStatusCode(), response.getStatusCode());

        validateAndAttachResponse(AttachmentName.GET_BLOG, response);
    }

    /**
     * This method calls Single Blog API
     * @param blogSlug
     * @method GET
     */
    public void getSingleBlog(String blogSlug) {
        System.out.println("getSingleBlog");

        response = requests.getRequest(EndPoints.singleBlog + blogSlug, "");

        System.out.println("Response :: " + response.asString());
        System.out.println("StatusCode :: " + response.getStatusCode());

        Assert.assertEquals(StatusCode.STATUS_OK.getStatusCode(), response.getStatusCode());

        validateAndAttachResponse(AttachmentName.GET_SINGLE_BLOG, response);
    }
}
