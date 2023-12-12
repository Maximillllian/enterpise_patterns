package table_data_gateway.gateway;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookGateway implements TableDataGateway {
    private Connection connection;

    private String SELECT_BY_ID = "SELECT * FROM book WHERE id = ?";
    private String SELECT_ALL = "SELECT * FROM book";

    public BookGateway(Connection connection) {
        this.connection = connection;
    }

    @Override
    public ResultSet get(long id) throws SQLException {
        var preparedStatement = connection.prepareStatement(SELECT_BY_ID);
        preparedStatement.setLong(1, id);
        return preparedStatement.executeQuery();
    }

    @Override
    public ResultSet getAll() throws SQLException {
        var statement = connection.createStatement();
        return statement.executeQuery(SELECT_ALL);
    }
}
