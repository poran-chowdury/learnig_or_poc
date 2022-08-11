package com.selise.processor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.selise.models.User;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.io.InputStream;

public class InsertUserProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        String jsonString=exchange.getIn().getBody(String.class);
        Gson gson=new GsonBuilder().create();

        User obj=gson.fromJson(jsonString, User.class);
        //exchange.getIn().setBody(obj);
        exchange.getContext().getTypeConverter().tryConvertTo(InputStream.class, exchange, obj);
    }
}
