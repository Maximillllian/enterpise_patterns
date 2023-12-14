package resource_patterns.resource_decorator.decorator;

import resource_patterns.resource_decorator.CustomConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class LoggerDecorator extends ConnectionDecorator {
    public LoggerDecorator(CustomConnection connection) {
        super(connection);
    }

    @Override
    public Statement createStatement() {
        System.out.println("Started...");
        var stmt = subject.createStatement();
        System.out.println("End...");
        return stmt;
    }
}
