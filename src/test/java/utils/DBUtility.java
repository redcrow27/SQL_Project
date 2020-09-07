package utils;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtility {

    private static String propertyPath = "src/test/resources/conf/configuration.properties";
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;


    //    PosgreSQL         demoHR
    public static void establishConnection(Database dbType, String database) {

        try {
            switch (dbType) {
                case POSTGRESQL:
                    connection = DriverManager.getConnection(ConfigReader.readProperty(database, propertyPath),
                            ConfigReader.readProperty("dbUserName", propertyPath),
                            ConfigReader.readProperty("dbPassword", propertyPath));
                    if (connection != null) {
                        System.out.println("Postgres DB connection Successfull");
                    } else {
                        System.out.println("Failed to connect to Postgres Database");
                    }
                    break;
                case ORACLE:
                    break;
                case MONGO:
                    break;
                case MYSQL:
                    break;
                default:
                    connection = null;
                    System.out.println(" default message");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getRowsCount(String query) {
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(query);
            resultSet.last();
           // System.out.println("Total count: " + resultSet.getRow());
            return resultSet.getRow();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static List<Map<String, Object>> getQueryResults(String query) {
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(query);

            List<Map<String, Object>> list = new ArrayList<>();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int coumnCount = resultSetMetaData.getColumnCount();

            while (resultSet.next()) {
                Map<String, Object> rowMap = new HashMap<>();
                for (int col = 1; col <= coumnCount; col++) {
                    rowMap.put(resultSetMetaData.getColumnName(col), resultSet.getObject(col));
                }
                list.add(rowMap);
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void closeConnections() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
            System.out.println("Database closed successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
