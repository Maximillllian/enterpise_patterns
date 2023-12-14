package data_accessor;

import data_accessor.dao.BookDAO;
import data_accessor.dao.DAO;
import data_accessor.dao.UserDAO;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        var dataAccessors = new ArrayList<DAO>();
        dataAccessors.add(new BookDAO());
        dataAccessors.add(new UserDAO());

        dataAccessors.forEach(it -> {
            var entity = it.getById("");
            System.out.println(it.getAll());
            System.out.println(entity);
            it.remove("");
            it.update(entity);
        });
    }
}
