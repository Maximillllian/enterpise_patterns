package resource_patterns.resource_decorator.decorator;

import resource_patterns.resource_decorator.CustomConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class ConnectionDecorator implements CustomConnection {
    protected CustomConnection subject;

    public ConnectionDecorator(CustomConnection connection) {
        this.subject = connection;
    }

    public abstract Statement createStatement();
}
