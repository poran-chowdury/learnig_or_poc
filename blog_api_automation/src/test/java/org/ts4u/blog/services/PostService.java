package org.ts4u.blog.services;

import io.restassured.response.Response;
import org.junit.Assert;
import org.ts4u.blog.enums.AttachmentName;
import org.ts4u.blog.enums.StatusCode;
import org.ts4u.blog.httprequests.EndPoints;

/**
 * @author TOWFIQUL ISLAM
 * @email towfiq.106@gmail.com
 * @since 11/27/21 1:20 AM
 */

public class PostService extends BaseService {

    protected Response response;

    /**
     * This method calls My Post API
     * @method GET
     */
    public void getMyPost() {
        System.out.println("getMyPost");

        response = requests.getRequest(EndPoints.myPost, "");

        System.out.println("Response :: " + response.asString());
        System.out.println("StatusCode :: " + response.getStatusCode());

        Assert.assertEquals(StatusCode.STATUS_OK.getStatusCode(), response.getStatusCode());

        validateAndAttachResponse(AttachmentName.MY_POST, response);
    }

    /**
     * This method calls  All Post API
     * @method GET
     */
    public void getAllPost() {
        System.out.println("getAllPost");

        response = requests.getRequest(EndPoints.getPosts, "");

        System.out.println("Response :: " + response.asString());
        System.out.println("StatusCode :: " + response.getStatusCode());

        Assert.assertEquals(StatusCode.STATUS_OK.getStatusCode(), response.getStatusCode());

        validateAndAttachResponse(AttachmentName.GET_POST, response);
    }

    /**
     * This method calls Single Post API
     * @method GET
     * @param postSlug
     */
    public void getSinglePost(String postSlug) {
        System.out.println("getSinglePost");

        response = requests.getRequest(EndPoints.singlePost + postSlug, "");

        System.out.println("Response :: " + response.asString());
        System.out.println("StatusCode :: " + response.getStatusCode());

        Assert.assertEquals(StatusCode.STATUS_OK.getStatusCode(), response.getStatusCode());

        validateAndAttachResponse(AttachmentName.GET_SINGLE_POST, response);
    }

    /**
     * This method calls Group Post API
     * @method GET
     * @param groupId
     */
    public void getGroupPost(String groupId) {
        System.out.println("getGroupPost");

        response = requests.getRequest(EndPoints.groupPost + groupId, "");

        System.out.println("Response :: " + response.asString());
        System.out.println("StatusCode :: " + response.getStatusCode());

        Assert.assertEquals(StatusCode.STATUS_OK.getStatusCode(), response.getStatusCode());

        validateAndAttachResponse(AttachmentName.GET_GROUP_POST, response);
    }

    /**
     * This method calls Share Post API
     * @method POST
     * @param postId
     */
    public void postSharePost(String postId) {
        System.out.println("postGroupPost");

        response = requests.postRequest(
                EndPoints.sharePost,
                bodyBuilder.postSharePostRequestBody(postId)
        );

        System.out.println("Response :: " + response.asString());
        System.out.println("StatusCode :: " + response.getStatusCode());

        Assert.assertEquals(StatusCode.STATUS_OK.getStatusCode(), response.getStatusCode());

        validateAndAttachResponse(AttachmentName.GET_GROUP_POST, response);
    }
}
