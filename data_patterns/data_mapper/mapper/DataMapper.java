package data_mapper.mapper;

import java.util.List;

public interface DataMapper<T, S> {
    void update(T entity);
    void insert(T entity);
    void delete(S id);
    T findById(S id);
    List<T> findAll();
}
