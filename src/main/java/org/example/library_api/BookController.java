package org.example.library_api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    private BookService bookService;
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("/book")
    public Book getBook(){
        return new Book(367,"sidhf","dsga",54658);
    }
    @PostMapping("/books")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        Optional<Book> savedBook = bookService.createBook(book);
        if (savedBook.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(savedBook.get());
        }
        else{
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/books/search")
    public String getSearchAuthor(@RequestParam String author){
        return "Поиск книг автора:" + author;
    }

    @GetMapping("/books/all")
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> allBooks = bookService.getBooks();
      return ResponseEntity.ok(allBooks);
    }
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id){
        Optional<Book> foundBook = bookService.getBook(id);
        if (foundBook.isPresent()) {
            return ResponseEntity.ok(foundBook.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable int id){
        Book foundBook = bookService.deleteBook(id);
        if (foundBook == null){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.noContent().build();
        }
    }
    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBookById(@PathVariable int id, @RequestBody Book book){
        Book foundBook = bookService.updateBook(id, book);
        if (foundBook == null){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok(foundBook);
        }
    }
}
