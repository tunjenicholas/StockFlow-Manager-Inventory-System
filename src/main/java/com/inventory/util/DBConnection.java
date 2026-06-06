package com.inventory.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USER = "inventory";
    private static final String PASSWORD = "inventory2026"; 

    private static Connection connection = null;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {

                try {
                    Class.forName("oracle.jdbc.OracleDriver");
                    System.out.println("Oracle Driver Loaded");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                connection = DriverManager.getConnection(URL, USER, PASSWORD);

                System.out.println("Database Connected Successfully!");
            }
        }
            catch (SQLException e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
        }

        return connection;
    }

}
