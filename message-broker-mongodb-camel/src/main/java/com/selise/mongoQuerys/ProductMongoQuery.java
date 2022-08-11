package com.selise.mongoQuerys;

public final class ProductMongoQuery {
    public  static  final  String Insert=MongoQueryBuilder.QueryBuilder("Products","insert");
    public  static final String GetById=MongoQueryBuilder.QueryBuilder("Products","findById");
    public  static final String GetByHeaderCondition=MongoQueryBuilder.QueryBuilder("Products","findOneByQuery");
    public  static final String GetAllByHeaderCondition=MongoQueryBuilder.QueryBuilder("Products","findAll");

}
