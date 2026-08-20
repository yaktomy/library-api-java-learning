package org.example.library_api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private AuthorMapper authorMapper;
    public AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper){
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }
    public Optional<AuthorResponse> createAuthor(AuthorResponse authorResponse){
        Author author = new Author(authorResponse.getName());
        Author savedAuthor = authorRepository.save(author);
        return Optional.of(authorMapper.toDto(savedAuthor));
    }
    public Page<AuthorResponse> getAllAuthors(Pageable pageable){
        Page<Author> authors = authorRepository.findAll(pageable);
        return authors.map(authorMapper::toDto);
    }
    public Optional<AuthorResponse> getAuthorById(Integer id){
       return authorRepository.findById(id).map(authorMapper::toDto);
    }
    public Optional<AuthorResponse> updateAuthor(Integer id, AuthorResponse authorResponse){
        return authorRepository.findById(id)
                .map(author -> {author.setName(authorResponse.getName());
                return authorMapper.toDto(author);
                });
    }
    public boolean deleteAuthor(Integer id){
        Optional<Author> foundAuthor = authorRepository.findById(id);
        if (foundAuthor.isEmpty()){
            return false;
        }
        authorRepository.delete(foundAuthor.get());
        return true;
    }
}
