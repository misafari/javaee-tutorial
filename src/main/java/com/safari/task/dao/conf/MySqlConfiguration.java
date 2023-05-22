package com.safari.task.dao.conf;

import java.sql.*;

public class MySqlConfiguration {
    private static MySqlConfiguration instance = null;
    private Connection connection = null;

    private MySqlConfiguration() {
    }

    public static MySqlConfiguration getInstance() {
        if (instance == null) instance = new MySqlConfiguration();
        return instance;
    }

    public Connection getConnection() {
        try {
            if (connection == null) {
                Class.forName("com.mysql.jdbc.Driver");
                var url = "jdbc:mysql://localhost/task_management?user=root&password=admin";
                connection = DriverManager.getConnection(url);
            }

            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
