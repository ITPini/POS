package com.nm;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQL {

    private static final String URL = "jdbc:mysql://sql7.freemysqlhosting.net:3306";
    private static final String USER = "sql7313399";
    private static final String PASS = "Nnr5MNfjPK";

    private Connection connection;
    private Properties properties;

    private Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", USER);
            properties.setProperty("password", PASS);
        }
        return properties;
    }

    public Connection connect(String _query) {
        try (Connection con = DriverManager.getConnection(URL, getProperties())) {
            Statement _st = con.createStatement();
            ResultSet _rs = _st.executeQuery(_query);

            if (_rs.next()) {
                System.out.println(_rs.getString(1));
            }

        } catch (SQLException ex) {
            Logger _lgr = Logger.getLogger(SQL.class.getName());
            _lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return connection;
    }

    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("Disconnected...");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
