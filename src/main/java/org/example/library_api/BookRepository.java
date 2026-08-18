package org.example.library_api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.function.Function;

public interface BookRepository extends JpaRepository<Book, Integer> {
List<Book> findByAuthor (String author);
List<Book> findByAuthorAndYear (String author, int year);
List<Book> findByYearGreaterThan (int year);
List<Book> findByTitleContaining (String titlePart);
@Query("SELECT b FROM Book b Where b.year >= :from AND b.year <= :to")
List<Book> findBooksByYearBetween(@Param("from") int from, @Param("to") int to);
@Query("SELECT b FROM Book b WHERE b.title LIKE CONCAT('%', :title, '%') ORDER BY b.year")
    List<Book> findBooksByTitle (@Param("title") String title);
Page<Book> findAll(Pageable pageable);
@Query("SELECT b FROM Book b Where b.author = :author")
    Page<Book> findBookByAuthorPage(@Param("author") String author,
                                    Pageable pageable);
}
