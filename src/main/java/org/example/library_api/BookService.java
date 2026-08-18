package org.example.library_api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
            Book savedBook = bookRepository.save(gotBook);
            return Optional.of(bookMapper.toDto(savedBook));

    }
    public List<BookDto> getBooksByAuthor(String author){
        return bookRepository.findByAuthor(author)
                .stream()
                .map(bookMapper::toDto)
                .toList();
    }
    public List<BookDto> getBooksByAuthorAndYear(String author, int year){
        return bookRepository.findByAuthorAndYear(author, year)
                .stream()
                .map(bookMapper::toDto)
                .toList();
    }
    public List<BookDto> getBooksByYearBetween(int yearFrom, int yearTo){
        return bookRepository.findBooksByYearBetween(yearFrom, yearTo)
                .stream()
                .map(bookMapper::toDto)
                .toList();
    }
    public List<BookDto> getBooksByTitle(String title){
        return bookRepository.findBooksByTitle(title)
                .stream()
                .map(bookMapper::toDto)
                .toList();
    }
    public Page<BookDto> getBooksPage(Pageable pageable){
        Page<Book> books = bookRepository.findAll(pageable);
        return books.map(bookMapper::toDto);
    }
    public Page<BookDto> getBooksByAuthorPage(String author, Pageable pageable){
        Page<Book> books = bookRepository.findBookByAuthorPage(author, pageable);
        return books.map(bookMapper::toDto);
    }
    public List<BookDto> getBooksYearGreaterThan(int year){
        return bookRepository.findByYearGreaterThan(year)
                .stream()
                .map(bookMapper::toDto)
                .toList();
    }
    public List<BookDto> getBooksTitleContaining(String titlePart){
        return bookRepository.findByTitleContaining(titlePart)
                .stream()
                .map(bookMapper::toDto)
                .toList();
    }
    public List<BookDto> getBooks(){
        return bookRepository.findAll()
                .stream().map(bookMapper::toDto)
                .toList();

    }
    public Optional<Book> getBook(int id){
        return bookRepository.findById(id);
    }
    public Optional<BookDto> getBookDto(int id){
        return bookRepository.findById(id)
                .map(bookMapper :: toDto);
    }
    public Book deleteBook(int id){
       if (bookRepository.existsById(id)){
           Optional<Book> foundBook = bookRepository.findById(id);
           Book book = foundBook.get();
           bookRepository.deleteById(book.getId());
           return book;
       }
       return null;
    }
    @Transactional
    public Book updateBook(int id, Book book){
        Optional<Book> foundBook = bookRepository.findById(id);
        if (foundBook.isPresent()){
            Book existingBook = foundBook.get();
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
            existingBook.setYear(book.getYear());
            return existingBook;
        }
        return null;
    }
}
