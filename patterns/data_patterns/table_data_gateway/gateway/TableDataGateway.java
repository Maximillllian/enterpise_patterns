package table_data_gateway.gateway;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface TableDataGateway {
    ResultSet get(long id) throws SQLException;
    ResultSet getAll() throws SQLException;
}
