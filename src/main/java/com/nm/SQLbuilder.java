package com.nm;

import javax.swing.JOptionPane;
import java.sql.*;
import java.util.*;

class SQLbuilder {
    private static final String URL = "jdbc:mysql://sql7.freemysqlhosting.net:3306";
    private static final String USER = "sql7313399";
    private static final String PASS = "Nnr5MNfjPK";
    public Connection connectivity;
    private Properties properties;

    private Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", USER);
            properties.setProperty("password", PASS);
        }
        return properties;
    }

    public void connect() {
        try {
            connectivity = DriverManager.getConnection(URL, getProperties());
            System.out.println("Connected...");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Forbindelse til serveren kunne ikke oprettes", "mySQL connection", JOptionPane.INFORMATION_MESSAGE);
            e.printStackTrace();
        }
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
        return queryResult;
    }
}
