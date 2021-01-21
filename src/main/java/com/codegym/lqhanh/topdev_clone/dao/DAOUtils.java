package com.codegym.lqhanh.topdev_clone.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DAOUtils {
    private static final String url = "jdbc:mysql://localhost:3306/topdev_clone";
    private static final String uname = "dev";
    private static final String password = "password";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Register JDBC Driver failed");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, uname, password);
    }

    public static void logColumnInfo(String rsName, ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        System.out.println("-----------------\n" +
                rsName + " columns: " + columnCount + "\n");
        for (int i = 1; i <= columnCount; i++ ) {
            String name = metaData.getColumnName(i);
            System.out.println(i + ": " + name);
        }
        System.out.println("-----------------\n");
    }

}
