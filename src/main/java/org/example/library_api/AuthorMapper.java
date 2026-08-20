package org.example.library_api;

import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {
    public AuthorResponse toDto(Author author){
        return new AuthorResponse(author.getId()
                , author.getName());
    }
}
