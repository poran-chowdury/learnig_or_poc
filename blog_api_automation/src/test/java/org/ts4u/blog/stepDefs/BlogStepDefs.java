package org.ts4u.blog.stepDefs;

import io.cucumber.java.en.Then;
import org.ts4u.blog.services.BlogService;

/**
 * @author TOWFIQUL ISLAM
 * @email towfiq.106@gmail.com
 * @since 11/28/21 12:15 AM
 */

public class BlogStepDefs {

    BlogService blogService = new BlogService();

    /**
     *
     */
    @Then("Call Blog Trending Blog API")
    public void callBlogTrendingBlogAPI() {
        blogService.getTrendingBlog();
    }

    /**
     * @param name
     * @param description
     */
    @Then("Call Blog Create New Blog API with name {string} and description {string}")
    public void callBlogCreateNewBlogAPI(String name, String description) {
        blogService.postCreateBlog(name, description);
    }

    /**
     * @param statusCode
     * @param success
     */
    @Then("Check created blog response for status code {string} and success {string}")
    public void checkCreatedBlogResponseForStatusCodeAndSuccess(String statusCode, String success) {
        blogService.verifyCreatedBlogResponse(Integer.parseInt(statusCode), Boolean.parseBoolean(success));
    }

    /**
     *
     */
    @Then("Call Blog My Blog API")
    public void callBlogMyBlogAPI() {
        blogService.getMyBlog();
    }

    /**
     *
     */
    @Then("Call Blog Get All Blog API")
    public void callBlogGetAllBlogAPI() {
        blogService.getAllBlogs();
    }

    /**
     * @param blogSlug
     */
    @Then("Call Blog Get Single Blog API with blogSlug {string}")
    public void callBlogGetSingleBlogAPIWithBlogSlug(String blogSlug) {
        blogService.getSingleBlog(blogSlug);
    }
}
