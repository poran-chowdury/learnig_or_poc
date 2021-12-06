package org.ts4u.blog.httprequests;


import org.ts4u.blog.providers.CustomProperties;

import java.util.HashMap;

/**
 * @author TOWFIQUL ISLAM
 * @email towfiq.106@gmail.com
 * @since 11/25/21 11:53 PM
 */

public class EndPoints {

    private static final HashMap<String, String> properties = CustomProperties.properties;

    public static final String baseUrl = properties.get("baseUrl");

    public static final String registration = baseUrl + properties.get("api.user.sign-up");
    public static String resendOtp = baseUrl + properties.get("api.user.resend-otp");
    public static final String userLogin = baseUrl + properties.get("api.user.sign-in");
    public static String userProfile = baseUrl + properties.get("api.user.profile");

    public static String myPost = baseUrl + properties.get("api.post.my-post");
    public static String getPosts = baseUrl + properties.get("api.post.get");
    public static String singlePost =  baseUrl + properties.get("api.post.single") + "/"; // + postSlug
    public static String groupPost =  baseUrl + properties.get("api.post.group") + "/"; // + groupId
    public static String delete =  baseUrl + properties.get("api.post.delete") + "/"; // + postId
    public static String sharePost =  baseUrl + properties.get("api.post.share-post");

    public static String trendingBlog =  baseUrl + properties.get("api.blog.trending-post");
    public static String createBlog =  baseUrl + properties.get("api.blog.create");
    public static String myBlog =  baseUrl + properties.get("api.blog.my-blog");
    public static String getBlogs =  baseUrl + properties.get("api.blog.all-blogs");
    public static String singleBlog =  baseUrl + properties.get("api.blog.single") + "/"; // + blogSlug

    public static String recentArticle = baseUrl + properties.get("api.article.recent");
    public static String singleArticle = baseUrl + properties.get("api.article.single") + "/"; // + articleSlug
}
