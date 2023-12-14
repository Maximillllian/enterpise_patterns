package active_record;

import _shared.db.FillDatabaseGateway;

public class Main {
    public static void main(String[] args) {
        FillDatabaseGateway.fillDatabase();
        var users = User.findAll();
        System.out.println("Initial users: " + users);
        users.get(0).delete();
        var newUser = new User("lucius", "Lucius Malfoy");
        newUser.insert();
        var updatedUsers = User.findAll();
        System.out.println("UpdatedUsers: " + updatedUsers);
        var userTwo = User.findById(2L);
        System.out.println("User with id = 2: " + userTwo);
        userTwo.setName("Updated user");
        userTwo.update();
        var updatedUserTwo = User.findById(2L);
        System.out.println("Updated user with id = 2: " + userTwo);
    }
}
