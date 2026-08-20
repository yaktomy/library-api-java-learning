package org.example.library_api;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Author {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "author")
    private List<Book> books;
    protected Author(){

    }
    public Author(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public Integer getId(){
        return id;
    }
    public void setName(String name){
        this.name = name;
    }
}
