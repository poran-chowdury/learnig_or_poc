package com.selise.mongoQuerys;

import com.selise.Application;

public final class UserMongoQuery {
    public  static  final  String InsertUser=MongoQueryBuilder.QueryBuilder("Users","insert");
    public  static final String GetUserById=MongoQueryBuilder.QueryBuilder("Users","findById");
    public  static final String GetUserByHeaderCondition=MongoQueryBuilder.QueryBuilder("Users","findOneByQuery");
    public  static final String GetAllUsersByHeaderCondition=MongoQueryBuilder.QueryBuilder("Users","findAll");

}
