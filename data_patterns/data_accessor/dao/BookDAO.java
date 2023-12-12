package data_accessor.dao;

import _shared.domain.Book;

import java.util.List;

public class BookDAO implements DAO<Book, String> {
    @Override
    public Book getById(String id) {
        return new Book();
    }

    @Override
    public List<Book> getAll() {
        return List.of(new Book(), new Book());
    }

    @Override
    public void remove(String id) {
        System.out.println("Book is deleted");
    }

    @Override
    public void update(Book entity) {
        System.out.println("Book is updated...");
    }
}
