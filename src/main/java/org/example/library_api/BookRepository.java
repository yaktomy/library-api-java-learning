package org.example.library_api;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Optional<Book> getBook(int id){
       for (Book book : books){
           if (book.getId() == id){
               return Optional.of(book);
           }
       }
       return Optional.empty();
    }
    public Book deleteBook(int id){
        for (Book book : books){
            if(book.getId() == id){
                Book foundBook = book;
                books.remove(foundBook);
                return foundBook;
            }
        }
        return null;
    }
    public Book updateBook(int id, Book book){
        for (Book booka : books){
            if(booka.getId() == id){
                Book foundBook = booka;
                foundBook.setTitle(book.getTitle());
                foundBook.setAuthor(book.getAuthor());
                foundBook.setYear(book.getYear());
                return foundBook;
            }
        }
        return null;
    }
}
