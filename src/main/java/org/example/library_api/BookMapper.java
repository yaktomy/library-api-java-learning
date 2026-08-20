package org.example.library_api;

import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    public BookResponse toDto(Book book){
        return new BookResponse(book.getId()
                , book.getTitle()
                , book.getAuthor().getName()
                , book.getYear());
    }
}
