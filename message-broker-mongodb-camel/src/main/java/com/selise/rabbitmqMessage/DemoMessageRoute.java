package com.selise.rabbitmqMessage;

import com.selise.Application;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class DemoMessageRoute extends RouteBuilder {
    private static final String RABBITMQ_XCHG = Application.RabbitMqConnectionUrl+"&autoDelete=false&routingKey=ievent&queue=demo_Message";

    @Override
    public void configure() throws Exception {
        from("direct:sendMessage")
        .log("Ready to send ::: ${body}")
        .process(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                String message=exchange.getIn().getBody(String.class);
                System.out.println(message);
                exchange.getMessage().setBody(message);
            }
        })
        .to(RABBITMQ_XCHG)
        .log("Send message")
        .end()
        //.log("Send Message")
        //.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200))
        //.setBody(simple("${body}"))
        //.to("log:cheese")
        ;
    }
}
