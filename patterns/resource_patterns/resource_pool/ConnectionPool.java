package resource_patterns.resource_pool;

import _shared.db.DataBase;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ConnectionPool {
    private static ConnectionPool instance;

    private int CONNECTIONS_SIZE = 10;

    private LinkedList<PooledConnection> availableConnections = new LinkedList<>();
    private LinkedList<PooledConnection> usedConnections = new LinkedList<>();

    public synchronized static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }

        return instance;
    }

    public PooledConnection getPooledConnection() {
        if (!availableConnections.isEmpty()) {
            var connection = availableConnections.pop();
            usedConnections.add(connection);
            return connection;
        }

        if (totalConnections() == CONNECTIONS_SIZE) {
            // TODO: должны ждать, пока не освободится
            return null;
        }

        if (availableConnections.isEmpty()) {
            var connection = new PooledConnection(DataBase.getConnection(), this);
            usedConnections.add(connection);
            return connection;
        }

        return null;
    }

    public void closeConnection(PooledConnection pooledConnection) {
        usedConnections.remove(pooledConnection);
        availableConnections.add(pooledConnection);
    }

    private int totalConnections() {
        return availableConnections.size() + usedConnections.size();
    }
}
