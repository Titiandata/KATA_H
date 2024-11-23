package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(50), " +
                "last_name VARCHAR(50), " +
                "age TINYINT)";

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {


            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void saveUser(String name, String lastName, byte age) {
        String insertQuery = "INSERT INTO users (name, last_name, age) VALUES (?, ?, ?)";

        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {


            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {


            preparedStatement.setLong(1, id);
            int rowsAffected = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public List<User> getAllUsers() {
        String query = "SELECT * FROM users";
        List<User> users = new ArrayList<>();

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("last_name");
                byte age = resultSet.getByte("age");
                long id = resultSet.getLong("id");


                User user = new User(name, lastName, age);
                user.setId(id);

                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            }

        return users;
    }




    @Override
    public void cleanUsersTable() {
        String sql = "DELETE FROM users";

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {

            int row = statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
