package org.example.library_api;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {
    private final List<Book> books = new ArrayList<>();
    public Book save(Book book){
        books.add(book);
        return book;
    }
    public List<Book> findAll(){
        return books;
    }
}
