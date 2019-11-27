package com.nm;

import java.sql.*;
import java.util.Properties;

public class SQL {

    private static final String URL = "jdbc:mysql://sql7.freemysqlhosting.net:3306";
    private static final String USER = "sql7313399";
    private static final String PASS = "Nnr5MNfjPK";

    private Connection connectivity;
    private Properties properties;
    private Statement statement;
    private ResultSet resultSet;

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

    public void executeQuery(String _query) {
        if (connectivity != null) {
            try {
                statement = connectivity.createStatement();
                resultSet = statement.executeQuery(_query);
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("ID"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                disconnect();
            }
        }
    }
}
