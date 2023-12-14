package _shared.db;

import java.sql.*;

public class DataBase {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:data_patterns.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");
            return conn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static Statement getStatement() {
        var connection = getConnection();
        if (connection == null) {
            System.out.println("Connection to SQLite failed");
        }

        try {
            return connection.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
