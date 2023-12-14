package data_accessor.dao;

import java.util.List;

public interface DAO<T, S> {
    T getById(S id);
    List<T> getAll();
    void remove(S id);
    void update(T entity);
}
