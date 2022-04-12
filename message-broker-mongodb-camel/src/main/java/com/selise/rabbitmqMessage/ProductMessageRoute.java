package com.selise.rabbitmqMessage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.selise.Application;
import com.selise.models.ProductEvent;
import com.selise.models.User;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class ProductMessageRoute extends RouteBuilder {
    private static final String RABBITMQ_XCHG = Application.RabbitMqConnectionUrl+"&autoDelete=false&routingKey=ievent&queue=demo_ProductCreatedEvent";
    @Override
    public void configure() throws Exception {
    from(RABBITMQ_XCHG)
    .log("${body}")
    //.convertBodyTo(String.class)
    /*.process(new Processor() {
        @Override
        public void process(Exchange exchange) throws Exception {
            String message=exchange.getIn().getBody(String.class);
            System.out.println(message);
            Gson gson=new GsonBuilder().create();

            ProductEvent obj=gson.fromJson(message, ProductEvent.class);
            System.out.println(obj);
        }
    })*/
    .to("direct:insertProduct")
    ;
    }
}
