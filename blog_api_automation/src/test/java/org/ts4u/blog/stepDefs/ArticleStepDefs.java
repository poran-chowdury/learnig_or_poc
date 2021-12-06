package org.ts4u.blog.stepDefs;

import io.cucumber.java.en.Then;
import org.ts4u.blog.services.ArticleService;

/**
 * @author TOWFIQUL ISLAM
 * @email towfiq.106@gmail.com
 * @since 11/29/21 12:27 AM
 */

public class ArticleStepDefs {

    ArticleService articleService = new ArticleService();

    /**
     *
     */
    @Then("Call Article Get Recent Article API")
    public void callArticleGetRecentArticleAPI() {
        articleService.getRecentArticle();
    }

    /**
     * @param articleSlug
     */
    @Then("Call Article Get Single Article API with articleSlug {string}")
    public void callArticleGetSingleArticleAPIWithArticleSlug(String articleSlug) {
        articleService.getSingleArticle(articleSlug);
    }
}
