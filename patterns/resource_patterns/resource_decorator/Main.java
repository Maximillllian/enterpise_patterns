package resource_patterns.resource_decorator;

import _shared.db.DataBase;
import resource_patterns.resource_decorator.decorator.LoggerDecorator;
import resource_patterns.resource_decorator.decorator.TimerDecorator;

public class Main {
    public static void main(String[] args) {
        var connection = new TimerDecorator(new LoggerDecorator(new TimerDecorator(new CustomConnectionImpl())));
        connection.createStatement();
    }
}
