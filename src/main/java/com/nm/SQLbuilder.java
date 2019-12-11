package com.nm;

import java.sql.*;
import java.util.*;

public class SQLbuilder {

    private static final String URL = "jdbc:mysql://sql7.freemysqlhosting.net:3306";
    private static final String USER = "sql7313399";
    private static final String PASS = "Nnr5MNfjPK";

    private Connection connectivity;
    private Properties properties;

    private Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", USER);
            properties.setProperty("password", PASS);
        }
        return properties;
    }

    public Connection connect() {
        try {
            connectivity = DriverManager.getConnection(URL, getProperties());
            System.out.println("Connected...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connectivity;
    }

    public void disconnect() {
        if (connectivity != null) {
            try {
                connectivity.close();
                connectivity = null;
                System.out.println("Disconnected...");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public String executeQuery(String _query) {
        String queryResult = null;
        if (connectivity != null) {
            try {
                Statement statement = connectivity.createStatement();
                ResultSet resultSet = statement.executeQuery(_query);
                while (resultSet.next()) {
                    queryResult = resultSet.getString(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println(queryResult);
        return queryResult;
    }
}
