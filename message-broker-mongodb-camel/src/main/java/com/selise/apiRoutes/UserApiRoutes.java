package com.selise.apiRoutes;

import com.mongodb.client.model.Filters;
import com.selise.Application;
import com.selise.models.User;
import com.selise.mongoQuerys.UserMongoQuery;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mongodb.MongoDbConstants;
import org.apache.camel.model.rest.RestBindingMode;
import org.bson.types.ObjectId;

public class UserApiRoutes extends RouteBuilder {
    private static final String APPLICATION_JSON = "application/json";
    private static  final String path="/user";
    @Override
    public void configure() throws Exception {

        /* For Rest */
        restConfiguration().component(Application.ApplicationHTTPComponent)
            .host(Application.ApplicationHost).port(Application.ApplicationHostPort).bindingMode(RestBindingMode.json);;

        rest(path)
        .post("/insert")
                .type(User.class)
                .bindingMode(RestBindingMode.json)
        //.route()
        //.setHeader("Content-Type", constant(APPLICATION_JSON))
        //.setHeader("Accept", constant(APPLICATION_JSON))
        .to("direct:insertUser")
        ;
/* End */
        from(UrlGenerator.GenerateGetUrl("user"))
                .log(UrlGenerator.GenerateGetUrl("user"))
                .log("Called findById API")
                .setBody(header("id"))
                .convertBodyTo(ObjectId.class)
                .log(UserMongoQuery.GetUserById)
                .to(UserMongoQuery.GetUserById)
                .setBody(simple("${body}"));


        from(UrlGenerator.GenerateGetUrl("user/getByEmail"))
                .log("Request to read user data. Header ${header.email}")
                .setHeader(MongoDbConstants.CRITERIA, constant(Filters.eq("email","${header.email}")))
                //.convertBodyTo(Object.class)
                .to(UserMongoQuery.GetUserByHeaderCondition)
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200))
                .setBody(simple("${body}"))
        ;

        from(UrlGenerator.GenerateGetUrl("user/getAll"))
                .log("Request to read user data. Header ${header.email}")
                .to(UserMongoQuery.GetAllUsersByHeaderCondition)
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200))
                .setBody(simple("${body}"))
        ;

        from(UrlGenerator.GeneratePostUrl("user/sendMessage"))
                .log("Request to read user data. Header ${header.email}")
                .to("direct:sendMessage")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200))
                .setBody(simple("${body}"))
        ;


        /*rest(path)
        .post("/sendMessage")
        .produces(APPLICATION_JSON)
        .to("direct:sendMessage")
        .responseMessage()
        .setMessage("Successfully send");
        ;*/
        rest(path)
        .get("/getUserDetail")
        .produces(APPLICATION_JSON)
        .to("direct:getById")
        ;
        rest(path)
        .get("/getUserByEmail/{email}")
        .produces(APPLICATION_JSON)
        .to("direct:getByEmail")
        ;

    }
}
