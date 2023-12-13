package row_data_gateway;

import _shared.db.FillDatabaseGateway;
import row_data_gateway.gateway.UserGateway;

public class Main {
    public static void main(String[] args) {
        FillDatabaseGateway.fillDatabase();
        var users = UserGateway.findAll();
        System.out.println("Initial users: " + users);
        users.get(0).delete();
        var newUser = new UserGateway("lucius", "Lucius Malfoy");
        newUser.insert();
        var updatedUsers = UserGateway.findAll();
        System.out.println("UpdatedUsers: " + updatedUsers);
        var userTwo = UserGateway.findById(2L);
        System.out.println("User with id = 2: " + userTwo);
    }
}
