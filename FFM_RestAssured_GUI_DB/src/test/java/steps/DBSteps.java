package steps;

import httprequests.EndPoints;
import org.postgresql.util.PSQLException;

import java.sql.*;

public class DBSteps {

    private final static String dbName = EndPoints.dbDetails().split(",")[2];
    private final static String dbHost = EndPoints.dbDetails().split(",")[0];
    private final static String dbPort = EndPoints.dbDetails().split(",")[1];
    private final static String dbUsername = EndPoints.dbDetails().split(",")[3];
    private final static String dbPassword = EndPoints.dbDetails().split(",")[4];
    private final static String dbUrl = "jdbc:postgresql://" + dbHost + ":" + dbPort + "/" + dbName;
    public static Connection dbConnection;
    public static ResultSet rs;
    public static Statement stm;

    /***
     * creates connection with db
     */
    public static void connectDB() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (java.lang.ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            System.out.println("db connected");
        } catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /***
     * executes the given query in the db
     * @param query
     * @throws SQLException
     */
    public static ResultSet executeQuery(String query) throws SQLException {
        stm = dbConnection.createStatement();
        try{
            rs = stm.executeQuery(query);
            System.out.println("Data retrieved from the PostgreSQL database ");
        }catch (PSQLException ex){
            System.out.println(ex.getMessage());
        }
        return rs;
    }

    /***
     * method to close the db connection
     */
    public static void closeDBConnection() throws SQLException {
        try {
            if (rs != null) rs.close();
            if (stm != null) stm.close();
            if (dbConnection != null) dbConnection.close();
            System.out.println("db connection closed");
        } catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            try { if (rs != null) rs.close();
                System.out.println("resultset closed");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            try { if (stm != null) stm.close();
                System.out.println("statement closed");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            try { if (dbConnection != null) dbConnection.close();
                System.out.println("db connection closed");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
