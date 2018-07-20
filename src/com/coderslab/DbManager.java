package com.coderslab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbManager {

    private static final String DB_URL = "";
    private static final String DB_USER = "";
    private static final String DB_PASS = "";

    private static DbManager instance;
    private static Connection connection;
    private DbManager() {}

    public static DbManager getInstance() {
        if(instance == null) {
            instance = new DbManager();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        if(connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        }
        return connection;
    }
}
