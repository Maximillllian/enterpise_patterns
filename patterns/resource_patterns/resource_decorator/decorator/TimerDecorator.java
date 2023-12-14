package resource_patterns.resource_decorator.decorator;

import resource_patterns.resource_decorator.CustomConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Timer;

public class TimerDecorator extends ConnectionDecorator {
    public TimerDecorator(CustomConnection connection) {
        super(connection);
    }

    @Override
    public Statement createStatement() {
        var start = System.nanoTime();
        var stmt = subject.createStatement();
        var end = System.nanoTime();
        System.out.println("Duration - " + (end - start));
        return stmt;
    }
}
