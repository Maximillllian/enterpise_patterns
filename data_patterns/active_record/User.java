package active_record;

import _shared.db.DataBase;
import row_data_gateway.gateway.UserGateway;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class User implements ActiveRecord {
    private long id;
    private String name;
    private String login;

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public User(String login, String name) {
        this.login = login;
        this.name = name;
    }

    public User() {}

    // Some business logic

    public static User findById(long id) {
        try (var connection = DataBase.getConnection()) {
            var script = "SELECT * FROM user WHERE ID = ?";
            var preparedStatement = connection.prepareStatement(script);
            preparedStatement.setLong(1, id);
            var resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                return null;
            }

            return from(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<User> findAll() {
        try (var stmt = DataBase.getStatement()) {
            var script = "SELECT * FROM user";
            var resultSet = stmt.executeQuery(script);

            var users = new ArrayList<User>();
            while (resultSet.next()) {
                users.add(from(resultSet));
            }

            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static User from(ResultSet resultSet) throws SQLException {
        var user = new User();

        user.setId(resultSet.getLong("id"));
        user.setLogin(resultSet.getString("login"));
        user.setName(resultSet.getString("name"));

        return user;
    }

    public void insert() {
        try (var connection = DataBase.getConnection()) {
            var script = "INSERT INTO user (name, login) VALUES (?, ?)";
            var preparedStatement = connection.prepareStatement(script);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, login);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete() {
        try (var connection = DataBase.getConnection()) {
            var script = "DELETE FROM user WHERE id = ?";
            var preparedStatement = connection.prepareStatement(script);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update() {
        try (var connection = DataBase.getConnection()) {
            var script = "UPDATE user " +
                "SET login = ?," +
                "name = ?" +
                "WHERE id = ?";
            var preparedStatement = connection.prepareStatement(script);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, name);
            preparedStatement.setLong(3, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "id: " + id + ", login: " + login + ", name: " + name;
    }
}
