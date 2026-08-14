package org.example.library_api;

import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    public BookDto toDto(Book book){
        return new BookDto(book.getId()
                , book.getTitle()
                , book.getAuthor()
                , book.getYear());
    }
    public Book toBook(BookDto bookDto){
        return new Book(bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getYear());
    }
}
