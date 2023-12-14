package resource_patterns.resource_decorator;

import java.sql.Statement;

public interface CustomConnection {
    Statement createStatement();
}
