package org.ts4u.blog.stepDefs;

import io.cucumber.java.en.Then;
import org.ts4u.blog.services.PostService;

/**
 * @author TOWFIQUL ISLAM
 * @email towfiq.106@gmail.com
 * @since 11/27/21 1:09 AM
 */

public class PostStepDefs {

    PostService postService = new PostService();

    /**
     *
     */
    @Then("Call Post MyPost API")
    public void callPostMyPostAPI() {
        postService.getMyPost();
    }

    /**
     *
     */
    @Then("Call Post Get API")
    public void callPostGetPostAPI() {
        postService.getAllPost();
    }

    /**
     * @param postSlug
     */
    @Then("Call Post Get Single Post API with postSlug {string}")
    public void callPostGetSinglePostAPIWithPostId(String postSlug) {
        postService.getSinglePost(postSlug);
    }

    /**
     * @param groupId
     */
    @Then("Call Post Get Group Post API with groupId {string}")
    public void callPostGetGroupPostAPIWithGroupId(String groupId) {
        postService.getGroupPost(groupId);
    }

    /**
     * @param postId
     */
    @Then("Call Post Get Share Post API with postId {string}")
    public void callPostGetSharePostAPIWithPostId(String postId) {
        postService.postSharePost(postId);
    }
}
