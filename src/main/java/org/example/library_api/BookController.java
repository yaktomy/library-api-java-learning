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


    @PostMapping("/books")
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto){
        Optional<BookDto> savedBook = bookService.createBook(bookDto);
        if (savedBook.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(savedBook.get());
        }
        else{
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/book/{id}/dto")
    public ResponseEntity<BookDto> getBookDtoById(@PathVariable int id){
        Optional<BookDto> foundBook = bookService.getBookDto(id);
        if (foundBook.isPresent()){
            BookDto bookDto = foundBook.get();
            return ResponseEntity.ok(bookDto);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/books/all")
    public ResponseEntity<List<BookDto>> getAllBooks(){
        List<BookDto> allBooks = bookService.getBooks();
      return ResponseEntity.ok(allBooks);
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
