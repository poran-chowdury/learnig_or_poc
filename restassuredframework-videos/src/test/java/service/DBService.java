package service;


import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.*;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import enums.MongoDBCollection;
import httprequests.EndPoints;
import org.bson.Document;
import org.bson.conversions.Bson;


public class DBService {

    private final static String dbHost = EndPoints.dbDetails().split(",")[0];
    private final static String dbPort = EndPoints.dbDetails().split(",")[1];
    private final static String dbName = EndPoints.dbDetails().split(",")[2];
    private final static String dbUsername = EndPoints.dbDetails().split(",")[3];
    private final static String dbPassword = EndPoints.dbDetails().split(",")[4];
    private final static String params = EndPoints.dbDetails().split(",")[5];

    private final static String dbUrl = "mongodb://" + dbUsername + ":" + dbPassword + "@" + dbHost + ":" + dbPort + "/" + dbName + params;

    public static MongoClient dbConnection;
    public static MongoDatabase database;
    public static MongoCollection<Document> collection;
    public static Document document;
    public static DeleteResult deleteResult;

    //Connect to the database
    public static void connectDB() {

        System.out.println("Connect to the database");
        try {
            System.out.println("Connecting to the database...");
            dbConnection = MongoClients.create(dbUrl);
            System.out.println("Connected to the database successfully");
            System.out.println("Mongo :: " + dbConnection.getClusterDescription());
            database = dbConnection.getDatabase(dbName);
            System.out.println("Database :: " + database.getName());
            System.out.println("Mongo DB Collection List:: ");
            MongoIterable<String> collections = database.listCollectionNames();
            for (String collection : collections) {
                System.out.println(collections);
            }
        } catch (MongoException ex) {
            System.err.println("DB Connection Exception :: " + ex.getMessage());
        }
    }

    //Database data is exist
    public static boolean isDBDataExist(MongoDBCollection mongoDBCollection, Bson query) {
        System.out.println("This data is exist in database");
        boolean dataFound = true;
        try {
            collection = database.getCollection(mongoDBCollection.getCollectionName());
            document = collection.find(query).first();
            System.out.println("Document :: " + document);
            if (document != null) {
                System.out.println("Data found successful. Collection ::" + mongoDBCollection.getCollectionName() + "Query ::" +
                        query);
            } else {
                System.out.println("Data found failed. Collection :: " + mongoDBCollection.getCollectionName() + "Query ::"
                        + query);
                dataFound = false;
            }
        } catch (MongoException ex) {
            System.err.println("Exception ::" + ex.getMessage());
            dataFound = false;
        }

        return dataFound;
    }

    //Delete query
    public static void deleteQuery() {
        try {
            deleteResult = collection.deleteOne(document);
            System.out.println("Delete Successfully. Document :: " + document);
        } catch (MongoException ex) {
            System.err.println("Exception :: " + ex.getMessage());
        }
    }

    // Close the DB connection
    public static void closeDBConnection() {
        try {
            if (dbConnection != null) dbConnection.close();
            System.out.println("DB Connection Closed");
        } catch (MongoException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //Create data in the Database
    public static void createDBData(MongoDBCollection mongoDBCollection, Document newDocument){
        try{
            collection=database.getCollection(mongoDBCollection.getCollectionName());
            collection.insertOne(newDocument);
            System.out.println("Created result :: "+ newDocument);
        }catch (MongoException ex){
            System.err.println("Unable to insert due to an error: "+ ex.getMessage());
        }

    }

    //Update data in the database
    public static void updateDBData(Bson update, UpdateOptions options){
        System.out.println("Update DB Data");
        try{
            UpdateResult result= null;
            if (options != null){
                result= collection.updateOne(document, update, options);
            }else{
                result= collection.updateOne(document, update);
            }
            if (result.wasAcknowledged()){
                System.out.println("Data Update Successfully");
            }else{
                System.out.println("Data Update Failed");
            }
        }catch (MongoException ex){
            System.err.println("Exception :: "+ex.getMessage());
        }
    }


}





