package resource_patterns.resource_pool;

import _shared.db.DataBase;

import java.sql.Connection;
import java.sql.SQLException;

public class CustomConnectionImpl implements CustomConnection {
    private Connection connection;

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void open() {
        connection = DataBase.getConnection();
    }
}
