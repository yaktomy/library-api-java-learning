package org.example.library_api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        Book savedBook = bookService.createBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }
    @GetMapping("book/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id){
        if(id == 1){
            return ResponseEntity.ok(new Book(1, "sjdh", "dsoiohd", 4678));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/books/search")
    public String getSearchAuthor(@RequestParam String author){
        return "Поиск книг автора:" + author;
    }

}
