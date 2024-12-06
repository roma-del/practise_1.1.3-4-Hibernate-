package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Util util = new Util();
    Connection connection = util.getConnection();
    private final String tableName = "table_name" + System.currentTimeMillis();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        String sql = "CREATE TABLE " + tableName
                + "(id INT PRIMARY KEY AUTO_INCREMENT NOT NULL, " +
                "name VARCHAR(250) NOT NULL, lastName VARCHAR(250) " +
                "NOT NULL, age tinyint NOT NULL);";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);


        } catch (SQLException ignore) {
        }


    }

    public void dropUsersTable() {
        String sql = "DROP TABLE " + tableName;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException ignore) {
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO " + tableName + "(name, lastname, age) VALUES (?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException ignore) {
        }

    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM " + tableName + " WHERE ID IN (" + id + ");";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException ignore) {
        }

    }

    public List<User> getAllUsers() {
        String sql = "select * FROM " + tableName + ";";
        List<User> list = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Long id = (long) resultSet.getInt("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                Byte age = resultSet.getByte("age");
                User user = new User(name,lastName,age);
                list.add(user);
                user.setId(id);
            }
        } catch (SQLException ignore) {
        }
        if (!list.isEmpty()) {
            return list;
        } else {
            return new ArrayList<>();
        }
    }

    public void cleanUsersTable() {
        String sql = "truncate " + tableName + ";";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException ignore) {
        }
    }
}
