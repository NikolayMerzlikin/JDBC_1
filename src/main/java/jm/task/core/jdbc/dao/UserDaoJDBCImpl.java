package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Statement statement;
    private PreparedStatement preparedStatement;
    private final Connection connection = Util.getConnection("localhost", "db_kata", "root", "1234");

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String queryOnCreate = "CREATE TABLE IF NOT EXISTS users (id INT AUTO_INCREMENT , name VARCHAR(50), lastname VARCHAR(50), age SMALLINT, PRIMARY KEY(id));";
        try {
            statement = connection.createStatement();
            statement.execute(queryOnCreate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String queryOnCreate = "DROP TABLE IF EXISTS users ;";
        try {
            statement = connection.createStatement();
            statement.execute(queryOnCreate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String query = "INSERT INTO users (name, lastname, age) VALUES (?,?,?);";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String query = "DELETE FROM users WHERE id=?;";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> listUsers = new ArrayList<>();
        String query = "SELECT * FROM users;";
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                listUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listUsers;
    }

    public void cleanUsersTable() {
        String queryOnCreate = "TRUNCATE users;";
        try {
            statement = connection.createStatement();
            statement.execute(queryOnCreate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
