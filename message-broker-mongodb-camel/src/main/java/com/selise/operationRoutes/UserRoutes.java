package com.selise.operationRoutes;

import com.mongodb.client.model.Filters;
import com.selise.apiRoutes.UrlGenerator;
import com.selise.mongoQuerys.UserMongoQuery;
import com.selise.processor.InsertUserProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mongodb.MongoDbConstants;
import org.bson.types.ObjectId;

public class UserRoutes extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("direct:insertUser")
                .log("Start to save user info")
                .process(new InsertUserProcessor())
                .log(UserMongoQuery.InsertUser)
                .to(UserMongoQuery.InsertUser)
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200))
                .setBody(constant("Successfully inserted"));
        ;

        from("direct:getById")
        .log("${header.id} :::: user ifo.")
        .convertBodyTo(ObjectId.class)
        .to(UserMongoQuery.GetUserById)
        .log("${body}")
        .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200))
        ;

        from("direct:getByEmail")
        .log("${header.id} :::: user ifo.")
        .convertBodyTo(ObjectId.class)
        .setHeader(MongoDbConstants.CRITERIA, constant(Filters.eq("email", header("email").toString())))
        .to(UserMongoQuery.GetUserByHeaderCondition)
        .log("${body}")
        .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200))
        ;
    }
}
