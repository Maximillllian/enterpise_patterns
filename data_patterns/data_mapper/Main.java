package data_mapper;

import _shared.db.FillDatabaseGateway;
import _shared.domain.User;
import data_mapper.mapper.UserMapper;

public class Main {
    public static void main(String[] args) {
        FillDatabaseGateway.fillDatabase();
        var mapper = new UserMapper();
        var users = mapper.findAll();
        System.out.println("Initial users: " + users);
        mapper.delete(users.get(0).getId());
        var newUser = new User("lucius", "Lucius Malfoy");
        mapper.insert(newUser);
        var updatedUsers = mapper.findAll();
        System.out.println("UpdatedUsers: " + updatedUsers);
        var userTwo = mapper.findById(2L);
        System.out.println("User with id = 2: " + userTwo);
        userTwo.setName("Updated user");
        mapper.update(userTwo);
        var updatedUserTwo = mapper.findById(2L);
        System.out.println("Updated user with id = 2: " + updatedUserTwo);
    }
}
