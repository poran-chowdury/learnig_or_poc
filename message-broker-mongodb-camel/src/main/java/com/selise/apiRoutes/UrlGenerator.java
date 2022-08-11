package com.selise.apiRoutes;

import com.selise.Application;

public final class UrlGenerator {
    public static  String GenerateGetUrl(String route){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Application.ApplicationHTTPComponent+":http://");
        stringBuilder.append(Application.ApplicationHost+":"+Application.ApplicationHostPort);
        stringBuilder.append("/"+route);
        stringBuilder.append("?httpMethodRestrict=GET");
        return stringBuilder.toString();
    }
    public static  String GeneratePostUrl(String route){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Application.ApplicationHTTPComponent+":http://");
        stringBuilder.append(Application.ApplicationHost+":"+Application.ApplicationHostPort);
        stringBuilder.append("/"+route);
        stringBuilder.append("?httpMethodRestrict=Post");
        return stringBuilder.toString();
    }
}
