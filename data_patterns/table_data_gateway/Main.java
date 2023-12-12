package table_data_gateway;

import _shared.db.DataBase;
import _shared.db.FillDatabaseGateway;
import table_data_gateway.gateway.BookGateway;
import table_data_gateway.gateway.TableDataGateway;
import table_data_gateway.gateway.UserGateway;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        FillDatabaseGateway.fillDatabase();

        try (var connection = DataBase.getConnection();) {
            List<TableDataGateway> gateways = List.of(new BookGateway(connection), new UserGateway(connection));

            for (var it : gateways) {
                var entities = it.getAll();
                while (entities.next()) {
                    System.out.println(entities.getLong("id") + " - " + entities.getString("name"));
                }
                System.out.println("---------------");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
