package org.ts4u.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author TOWFIQUL ISLAM
 * @email towfiq.106@gmail.com
 * @since 11/26/21 12:01 AM
 */

@Getter
@AllArgsConstructor
public enum AttachmentName {

    CREATE_USER("createUser"),
    RESEND_OTP("resendOtp"),
    LOGIN("login"),
    MY_POST("myPost"),
    GET_POST("getPost"),
    GET_SINGLE_POST("singlePost"),
    GET_GROUP_POST("groupPost"),
    TRENDING_BLOG("trendingBlog"),
    CREATE_BLOG("createBlog"),
    MY_BLOG("myBlog"),
    GET_BLOG("getBlog"),
    GET_SINGLE_BLOG("singleBlog"),
    RECENT_ARTICLE("recentArticle"),
    GET_SINGLE_ARTICLE("singleArticle"),
    ;

    private String attachmentName;
}

