package resource_patterns.resource_pool;

import java.sql.Connection;
import java.sql.Statement;

public class PooledConnection implements CustomConnection {
    private final ConnectionPool connectionPool;
    private final Connection connection;

    public PooledConnection(Connection connection, ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
        this.connection = connection;
    }


    @Override
    public void close() {
        connectionPool.closeConnection(this);
    }

    @Override
    public void open() {

    }
}
