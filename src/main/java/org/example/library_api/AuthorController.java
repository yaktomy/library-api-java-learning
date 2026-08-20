package org.example.library_api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthorController {
    private final AuthorService authorService;
    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }
    @PostMapping("/authors")
    public ResponseEntity<AuthorResponse> createAuthor(@RequestBody AuthorResponse authorResponse){
        AuthorResponse author = authorService.createAuthor(authorResponse).get();
        return ResponseEntity.status(HttpStatus.CREATED).body(author);
    }
    @GetMapping("/authors/page")
    public ResponseEntity<Page<AuthorResponse>> getAllAuthors(Pageable pageable){
        return ResponseEntity.ok(authorService.getAllAuthors(pageable));
    }
    @GetMapping("/authors/{id}")
    public ResponseEntity<AuthorResponse> getAuthorById(@PathVariable Integer id){
        return authorService.getAuthorById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PutMapping("/authors/{id}")
    public ResponseEntity<AuthorResponse> updateAuthor(@PathVariable Integer id, @RequestBody AuthorResponse authorResponse){
        return authorService.updateAuthor(id, authorResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @DeleteMapping("/authors/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Integer id){
        boolean deleted = authorService.deleteAuthor(id);
        if (!deleted){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
