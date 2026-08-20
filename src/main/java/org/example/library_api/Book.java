package org.example.library_api;
import jakarta.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
    private int year;
    protected Book(){

    }
    public Book(String title, Author author, int year){
        this.title = title;
        this.author = author;
        this.year = year;
    }
    public Integer getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public Author getAuthor(){
        return author;
    }
    public int getYear(){
        return year;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setAuthor(Author author){
        this.author = author;
    }
    public void setYear(int year){
        this.year = year;
    }
}
