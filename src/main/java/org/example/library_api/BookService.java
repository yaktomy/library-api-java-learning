package org.example.library_api;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }
    public Optional<Book> createBook(Book book){
        Optional<Book> creatingBook = getBook(book.getId());
        if (creatingBook.isPresent()){
            return Optional.empty();
        }
        else {

            return Optional.of(bookRepository.save(book));
        }
    }
    public List<Book> getBooks(){
        return bookRepository.findAll();
    }
    public Optional<Book> getBook(int id){
        return bookRepository.getBook(id);
    }
    public Book deleteBook(int id){
        return bookRepository.deleteBook(id);
    }
    public Book updateBook(int id, Book book){
        return bookRepository.updateBook( id, book);
    }
}
