package ecom.service;

import com.mongodb.MongoException;
import com.mongodb.client.*;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import ecom.enums.MongoDBCollection;
import ecom.httprequests.EndPoints;
import org.bson.Document;
import org.bson.conversions.Bson;

public class DBService {

    private final static String dbName = EndPoints.dbDetails().split(",")[2];
    private final static String dbHost = EndPoints.dbDetails().split(",")[0];
    private final static String dbPort = EndPoints.dbDetails().split(",")[1];
    private final static String dbUsername = EndPoints.dbDetails().split(",")[3];
    private final static String dbPassword = EndPoints.dbDetails().split(",")[4];
    private final static String params = EndPoints.dbDetails().split(",")[5];
    private final static String dbUrl = "mongodb://" + dbUsername + ":" + dbPassword + "@" + dbHost + ":" + dbPort + "/" + dbName + params;

    public static MongoClient dbConnection;
    public static MongoDatabase database;
    public static MongoCollection<Document> collection;
    public static Document document;
    public static DeleteResult deleteResult;

    /***
     * creates connection with db
     */
    public static void connectDB() {

        System.out.println("connectDB");
        System.out.println("Connected to the database successfully");

        try {

            System.out.println("Connecting database...");
            dbConnection = MongoClients.create(dbUrl);
            System.out.println("Connected to the database successfully");
            System.out.println("Mongo :: " + dbConnection.getClusterDescription());

            //Accessing the database
            database = dbConnection.getDatabase(dbName);
            System.out.println("Database :: " + database.getName());

            System.out.println("Mongo DB Collection List :: ");
            MongoIterable<String> collections = database.listCollectionNames();
            for (String collection : collections) {
                System.out.println(collection);
            }

            System.out.println("---------------------------------------");

        } catch (MongoException ex) {
            System.err.println("DB Connect Exception :: " + ex.getMessage());
        }
    }

    /**
     * @param mongoDBCollection
     * @param query
     * @return
     */
    public static boolean isDBDataExist(MongoDBCollection mongoDBCollection, Bson query) {
        System.out.println("isDBDataExist");

        boolean dataFound = true;

        try {

            collection = database.getCollection(mongoDBCollection.getCollectionName());
            document = collection.find(query).first();
            System.out.println("Document :: " + document);

            if (document != null) {
                System.out.println("Data found successful. Collection :: " + mongoDBCollection.getCollectionName() + " Query :: " + query);
            } else {
                System.out.println("Data found failed. Collection :: " + mongoDBCollection.getCollectionName() + " Query :: " + query);
                dataFound = false;
            }

        } catch (MongoException ex) {
            System.err.println("Exception :: " + ex.getMessage());
            dataFound = false;
        }

        return dataFound;

    }

    /**
     * @param mongoDBCollection
     */
    public static void getDBCollectionData(MongoDBCollection mongoDBCollection) {
        System.out.println("getDBCollectionData");

        try {

            collection = database.getCollection(mongoDBCollection.getCollectionName());

            System.out.println("Data found successful. Collection Name:: " + mongoDBCollection.getCollectionName() + " Collection :: " + collection);

        } catch (MongoException ex) {
            System.err.println("Exception :: " + ex.getMessage());
        }

    }

    /***
     * executes the given query in the db
     */
    public static void deleteQuery() {
        System.out.println("deleteQuery");

        try {

            deleteResult = collection.deleteOne(document);

            System.out.println("Delete successful. Document :: " + document);

        } catch (MongoException ex) {
            System.err.println("Exception :: " + ex.getMessage());
        }
    }

    /***
     * method to close the db connection
     */
    public static void closeDBConnection() {
        System.out.println("closeDBConnection");
        try {

            if (dbConnection != null) dbConnection.close();

            System.out.println("db connection closed");

        } catch (MongoException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createDBData(MongoDBCollection mongoDBCollection, Document newDocument) {
        System.out.println("updateDBData");

        try {

            collection = database.getCollection(mongoDBCollection.getCollectionName());
            collection.insertOne(newDocument);

            System.out.println("Created result :: " + newDocument);

        } catch (MongoException ex) {
            System.err.println("Unable to insert due to an error: " + ex.getMessage());
        }
    }

    /**
     * @param update
     */
    public static void updateDBData(Bson update, UpdateOptions options) {
        System.out.println("updateDBData");

        try {

            UpdateResult result = null;
            if (options != null) {
                result = collection.updateOne(document, update, options);
            } else {
                result = collection.updateOne(document, update);
            }

            System.out.println("Updated result :: " + result);

            if (result.wasAcknowledged()) {
                System.out.println("Data update successful. Document :: " + document + " Update :: " + update);
            } else {
                System.out.println("Data update failed. Document :: " + document + " Update :: " + update);
            }

        } catch (MongoException ex) {
            System.err.println("Exception :: " + ex.getMessage());
        }
    }

}
