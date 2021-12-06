package org.ts4u.blog.services;

import io.restassured.response.Response;
import org.junit.Assert;
import org.ts4u.blog.enums.AttachmentName;
import org.ts4u.blog.enums.StatusCode;
import org.ts4u.blog.httprequests.EndPoints;

/**
 * @author TOWFIQUL ISLAM
 * @email towfiq.106@gmail.com
 * @since 11/29/21 12:31 AM
 */

public class ArticleService extends BaseService {

    protected Response response;

    /**
     * This method call the Recent Article API
     * @method GET
     */
    public void getRecentArticle() {
        System.out.println("getRecentArticle");

        response = requests.getRequest(EndPoints.recentArticle, "");

        System.out.println("Response :: " + response.asString());
        System.out.println("StatusCode :: " + response.getStatusCode());

        Assert.assertEquals(StatusCode.STATUS_OK.getStatusCode(), response.getStatusCode());

        validateAndAttachResponse(AttachmentName.RECENT_ARTICLE, response);
    }

    /**
     * This method call Single Article API
     * @param articleSlug
     * @method GET
     */
    public void getSingleArticle(String articleSlug) {
        System.out.println("getSingleArticle");

        response = requests.getRequest(EndPoints.singleArticle + articleSlug, "");

        System.out.println("Response :: " + response.asString());
        System.out.println("StatusCode :: " + response.getStatusCode());

        Assert.assertEquals(StatusCode.STATUS_OK.getStatusCode(), response.getStatusCode());

        validateAndAttachResponse(AttachmentName.GET_SINGLE_ARTICLE, response);
    }
}
