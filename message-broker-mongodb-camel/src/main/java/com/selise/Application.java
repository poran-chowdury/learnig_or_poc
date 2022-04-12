package com.selise;

import com.mongodb.client.MongoClients;
import com.selise.apiRoutes.UserApiRoutes;
import com.selise.operationRoutes.ProductRoutes;
import com.selise.operationRoutes.UserRoutes;
import com.selise.rabbitmqMessage.DemoMessageRoute;
import com.selise.rabbitmqMessage.ProductMessageRoute;
import org.apache.camel.main.Main;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class Application {
    private Main main;
    private static final String mongoUrl;
    public static final String MongoCollection;
    public static final String RabbitMqConnectionUrl;
    public static final String DataSource="applicationDb";
    public static final String ApplicationHost;
    public static final String ApplicationHostPort;
    public static final String ApplicationHTTPComponent;
    static {
        Properties properties = new Properties();
        ClassLoader loader = Application.class.getClassLoader();
        try (InputStream resourceStream = loader.getResourceAsStream("application.properties")) {
            properties.load(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mongoUrl ="mongodb://"+ properties.getProperty("MongodbServer")+":"+properties.getProperty("MongoDbPort");
        MongoCollection =properties.getProperty("MongoCollection");
        ApplicationHost=properties.getProperty("AppHost");
        ApplicationHostPort=properties.getProperty("AppPort");
        ApplicationHTTPComponent=properties.getProperty("HttpComponent");
        RabbitMqConnectionUrl=properties.getProperty("RabbitMqUrl");
    }
    private  Application(){

    }
    public static void main(String[] args) throws Exception{
        Application application=new Application();
        application.start();
    }
    private void start() throws Exception {
        System.out.println(mongoUrl);
        System.out.println(MongoCollection);
        main = new Main();
        main.bind(DataSource, MongoClients.create(mongoUrl));
        main.configure().addRoutesBuilder(new UserApiRoutes());
        main.configure().addRoutesBuilder(new UserRoutes());
        main.configure().addRoutesBuilder(new ProductMessageRoute());
        main.configure().addRoutesBuilder(new ProductRoutes());
        main.configure().addRoutesBuilder(new DemoMessageRoute());
        System.out.println("Starting Camel. Use CTRL + C to terminate the process.\n");
        main.run();
    }
}
