package _shared.db;

import java.sql.Connection;
import java.sql.SQLException;

public class FillDatabaseGateway {
    private static String DROP_BOOK_TABLE = "DROP TABLE IF EXISTS book;";
    private static String DROP_USER_TABLE = "DROP TABLE IF EXISTS user;";

    private static String CREATE_USERS_TABLE = "CREATE TABLE user (" +
        "ID integer NOT NULL PRIMARY KEY, " +
        "name varchar(255), " +
        "login varchar (255) " +
        ")";

    private static String CREATE_BOOK_TABLE = "CREATE TABLE book (" +
        "ID integer NOT NULL PRIMARY KEY, " +
        "name varchar(255) " +
        ")";

    private static String ADD_USER = "INSERT INTO user (name, login) VALUES (?, ?)";
    private static String ADD_BOOK = "INSERT INTO book (name) VALUES (?)";

    public static void fillDatabase() {
        try (var connection = DataBase.getConnection()) {
            var statement = connection.createStatement();
            statement.execute(DROP_BOOK_TABLE);
            statement.execute(DROP_USER_TABLE);
            statement.execute(CREATE_BOOK_TABLE);
            statement.execute(CREATE_USERS_TABLE);

            addUser(connection, "Max Italy", "max");
            addUser(connection,  "Nik Rus", "nik");
            addUser(connection,  "Rosa Hutor", "rose");

            addBook(connection, "Экстраневинность");
            addBook(connection, "Демоны");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void addUser(Connection connection, String name, String login) throws SQLException {
        var preparedStatement = connection.prepareStatement(ADD_USER);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, login);
        preparedStatement.execute();
    }

    private static void addBook(Connection connection, String name) throws SQLException {
        var preparedStatement = connection.prepareStatement(ADD_BOOK);
        preparedStatement.setString(1, name);
        preparedStatement.execute();
    }
}
