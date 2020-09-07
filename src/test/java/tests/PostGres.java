package tests;


import org.junit.Assert;
import org.junit.Test;

import java.sql.*;
import java.util.*;

public class PostGres {

    // Path for postgresql database : url link link to set up a connection
    String demoHR = "jdbc:postgresql://LocalHost:5432/demoHR";

    // Create a username
    String dbUsername = "postgres";

    // Enter a password
    String dbPassword = "";


    @Test
    public void countTest() throws SQLException {
        // Stablish a Database Connection
        // for JDBC connection we use interface Connection
        Connection connection = DriverManager.getConnection(demoHR, dbUsername, dbPassword);

        // Establish an interface called Statement to read from the database
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        // ResultSet interface allow us to query and execute the command
        ResultSet resultSet = statement.executeQuery("select * from departments");

        // last row of the table
        resultSet.last();

        // Verify that the query contains more than 0 records (rows)
        int rowsCount = resultSet.getRow(); // 9
        System.out.println(rowsCount);
        Assert.assertTrue(rowsCount > 0);

        resultSet.close();
        statement.close();
        connection.close();

    }

    @Test
    public void testDatabaseConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(demoHR, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from departments");


        // navigate inside the table rows and columns to retrieve result
       // resultSet.first();

        while(resultSet.next()) {
            // print the column with index 1
            System.out.println(resultSet.getString(1) + " " +
                    resultSet.getString("dept_no") + " " +
                    resultSet.getString("dept_name"));
        }

        resultSet.close();
        statement.close();
        connection.close();

    }

    @Test
    public void metaData() throws SQLException {
        Connection connection = DriverManager.getConnection(demoHR, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String sql = "select * from departments";
        ResultSet resultSet = statement.executeQuery(sql);


        // Metadata
        // Metadata is the describing the data that is being stored in your Data source
        // Metadata generally includes the name, size and number of rows for each table present
        DatabaseMetaData dbMetadata = connection.getMetaData();

        // Return username of the database
        System.out.println("Database User: " + dbMetadata.getUserName());

        // Return database type
        System.out.println("Database type: " + dbMetadata.getDatabaseProductName());

        // ResultSet Metadata will query the results
        ResultSetMetaData rsMetaData = resultSet.getMetaData();

        System.out.println("Column count: " + rsMetaData.getColumnCount());

        for(int i = 1; i <= rsMetaData.getColumnCount(); i++) {
            System.out.println(i + " --> " + rsMetaData.getColumnName(i));
        }

        //----------------------------
        List<Map<String, Object >> list = new ArrayList<>();
        ResultSetMetaData rsMetaData2 = resultSet.getMetaData();

        int colCount = rsMetaData2.getColumnCount(); // numerical value from database

        while(resultSet.next()) {
            Map<String, Object> rowMap = new HashMap<>();
            for(int col = 1; col <= colCount; col++) {
                rowMap.put(rsMetaData2.getColumnName(col), resultSet.getObject(col));
            }
            list.add(rowMap);
        }

        for(Map<String, Object> emp: list) {
            System.out.println(emp.get("dept_no") + "  |  " + emp.get("dept_name"));
        }

        resultSet.close();
        statement.close();
        connection.close();

    }



}
