package data_accessor.dao;

import data_accessor.domain.User;

import java.util.List;

public class UserDAO implements DAO<User, String> {
    @Override
    public User getById(String id) {
        return new User();
    }

    @Override
    public List<User> getAll() {
        return List.of(new User(), new User());
    }

    @Override
    public void remove(String id) {
        System.out.println("User is deleted");
    }

    @Override
    public void update(User entity) {
        System.out.println("User is updated...");
    }
}
