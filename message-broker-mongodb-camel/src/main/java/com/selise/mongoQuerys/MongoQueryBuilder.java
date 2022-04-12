package com.selise.mongoQuerys;

import com.selise.Application;

public final class MongoQueryBuilder {
    public static String QueryBuilder(String collection,String operation){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("mongodb:");
        stringBuilder.append(Application.DataSource);
        stringBuilder.append("?database=");
        stringBuilder.append(Application.MongoCollection);
        stringBuilder.append("&collection="+collection);
        stringBuilder.append("&operation="+operation);

        return stringBuilder.toString();
    }
}
