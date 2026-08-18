package org.example.library_api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @GetMapping("/books/search/year")
    public ResponseEntity<List<BookDto>> getBooksYearGreaterThan(@RequestParam int year){
        return ResponseEntity.ok(bookService.getBooksYearGreaterThan(year));
    }
    @GetMapping("/books/search/title")
    public ResponseEntity<List<BookDto>> getBooksTitleContaining(@RequestParam String titlePart){
        return ResponseEntity.ok(bookService.getBooksTitleContaining(titlePart));
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

    @GetMapping("/books")
    public ResponseEntity<List<BookDto>> getAllBooks(
            @RequestParam(required = false) String author,
            @RequestParam(required = false) Integer year)
    {
        if (author != null && year != null){
            return ResponseEntity.ok(bookService.getBooksByAuthorAndYear(author,year));
        }
        if (author!=null){
            return ResponseEntity.ok(bookService.getBooksByAuthor(author));
        }
      return ResponseEntity.ok(bookService.getBooks());
    }
    @GetMapping("/books/search")
    public ResponseEntity<List<BookDto>> getBooksByYearBetween(
            @RequestParam(required = false) Integer from,
            @RequestParam(required = false) Integer to,
            @RequestParam(required = false) String title
    ) {
        if (title != null) {
            return ResponseEntity.ok(bookService.getBooksByTitle(title));
        } else {
            return ResponseEntity.ok(bookService.getBooksByYearBetween(from, to));
        }
    }
    @GetMapping("/books/page")
    public ResponseEntity<Page<BookDto>> getBooksPage(@RequestParam(required = false) String author,
                                                      Pageable pageable){
        if (author!=null){
            return ResponseEntity.ok(bookService.getBooksByAuthorPage(author, pageable));
        }
        return ResponseEntity.ok(bookService.getBooksPage(pageable));
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
