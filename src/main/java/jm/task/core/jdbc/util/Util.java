package jm.task.core.jdbc.util;

import java.sql.*;



public class Util {
    // реализуйте настройку соеденения с БД

    String url = "jdbc:mysql://localhost:3306/testdatabase";
    String user = "root";
    String password = "root";

    public Connection getConnection() {
        Connection connection = null;
        try {

            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
