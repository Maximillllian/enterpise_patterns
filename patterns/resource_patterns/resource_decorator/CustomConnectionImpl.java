package resource_patterns.resource_decorator;

import _shared.db.DataBase;

import java.sql.Statement;

public class CustomConnectionImpl implements CustomConnection {
    @Override
    public Statement createStatement() {
        return DataBase.getStatement();
    }
}
