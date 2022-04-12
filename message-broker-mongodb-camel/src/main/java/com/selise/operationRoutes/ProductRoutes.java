package com.selise.operationRoutes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.DBObject;
import com.selise.models.ProductEvent;
import com.selise.mongoQuerys.ProductMongoQuery;
import com.selise.mongoQuerys.UserMongoQuery;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import java.io.InputStream;

public class ProductRoutes extends RouteBuilder {
    private static final String APPLICATION_JSON = "application/json";

    @Override
    public void configure() throws Exception {
        from("direct:insertProduct")
        .log("Insert Product ::: ${body}")
        .log(ProductMongoQuery.Insert)
        .process(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                String message=exchange.getIn().getBody(String.class);
                System.out.println(message);
                Gson gson=new GsonBuilder().create();

                ProductEvent obj=gson.fromJson(message, ProductEvent.class);
                //exchange.getContext().getTypeConverter().tryConvertTo(InputStream.class, exchange, obj);
                exchange.getIn().setBody(obj,ProductEvent.class);
            }
        })

        .to(ProductMongoQuery.Insert)
        .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200))
        .setBody(constant("Successfully inserted"))
        ;
    }
}
