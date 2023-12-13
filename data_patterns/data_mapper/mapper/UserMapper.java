package data_mapper.mapper;

import _shared.db.DataBase;
import _shared.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserMapper implements DataMapper<User, Long> {
    public User findById(Long id) {
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

    public List<User> findAll() {
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

    public void insert(User user) {
        try (var connection = DataBase.getConnection()) {
            var script = "INSERT INTO user (name, login) VALUES (?, ?)";
            var preparedStatement = connection.prepareStatement(script);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
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
    public void update(User user) {
        try (var connection = DataBase.getConnection()) {
            var script = "UPDATE user " +
                "SET login = ?," +
                "name = ?" +
                "WHERE id = ?";
            var preparedStatement = connection.prepareStatement(script);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setLong(3, user.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
