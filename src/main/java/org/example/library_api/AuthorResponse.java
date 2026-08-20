package org.example.library_api;

public class AuthorResponse {
    private Integer id;
    private String name;
    protected AuthorResponse(){

    }
    public AuthorResponse(Integer id, String name){
        this.id = id;
        this.name = name;
    }
    public Integer getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
}
