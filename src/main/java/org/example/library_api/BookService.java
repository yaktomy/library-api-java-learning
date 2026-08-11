package org.example.library_api;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    public BookService(BookRepository bookRepository, BookMapper bookMapper){
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }
    public Optional<BookDto> createBook(BookDto bookDto){
        Book gotBook = bookMapper.toBook(bookDto);
        Optional<Book> creatingBook = getBook(gotBook.getId());
        if (creatingBook.isPresent()){
            return Optional.empty();
        }
        else {
            Book savedBook = bookRepository.save(gotBook);
            return Optional.of(bookMapper.toDto(savedBook));
        }
    }
    public List<Book> getBooks(){
        return bookRepository.findAll();
    }
    public Optional<Book> getBook(int id){
        return bookRepository.getBook(id);
    }
    public Optional<BookDto> getBookDto(int id){
        Optional<Book> book = bookRepository.getBook(id);
        if (book.isPresent()){
            Book foundBook = book.get();
           return Optional.of(bookMapper.toDto(foundBook));
        }
        else{
            return Optional.empty();
        }
    }
    public Book deleteBook(int id){
        return bookRepository.deleteBook(id);
    }
    public Book updateBook(int id, Book book){
        return bookRepository.updateBook( id, book);
    }
}
