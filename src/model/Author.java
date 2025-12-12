/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstoreapi.model;

/**
 * 
 * @author Hamza Hassan W2044381.
 */

// This class is for the authors of the books, it will have their id, name, and a short bio.
public class Author {
    private int authorId;
    private String Authorname;
    private String Authorbio;
    private String AuthorNationality;
    private String Authorawards;
    private String AuthorGenre;
    
    // empty constructor, needed for when the API converts JSON into objects automatically.
    public Author()  {}
    
    // This is my first constructor where i pass all the variables like the Author's id, name and biography.
    public Author(int id, String Authorname, String Authorbio, String Authorawards, String AuthorGenre, String AuthorNationality) {
        this.authorId = id;
        this.Authorname = Authorname;
        this.Authorbio = Authorbio;
        this.Authorawards = Authorawards;
        this.AuthorGenre = AuthorGenre;
        this.AuthorNationality = AuthorNationality;
    }
    
    // get method to get the Author's ID,this is because this links to the book id and the author is key information that should come with the book/item.
    public int getAuthorId() {
        return authorId;
    }

    // set method to set the Author's ID, might need to set or update a Author id.
    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }
    
    // get method to get the Author's name, this is another infromative key part of what should come with a book, a book requires the authors name on it for clarity with the customers.
    public String getAuthorName() {
        return Authorname;
    }
    
    // set method to set the Author's Name, might need to update or change the authors name.
    public void setAuthorName(String Authorname) {
        this.Authorname = Authorname;
    }
    
    // get method to get the Author's Biography, this is informative info for the customers.
    public String getAuthorBio() {
        return Authorbio;
    }
    
    // set method to set the Author's Biography, might need to update or set the authors biography.
    public void setAuthorBio(String Authorbio) {
        this.Authorbio = Authorbio;
    }
    
    // get method to get the awards that were given and attained by this author, this is good info for the customer to know how successful the author may be.
    public String getAuthorawards(){
        return Authorawards;
    }
    
    // set method to set the authors awards, there may be new awards that the author may have got that need to be added to the system for the customer to know.
    public void setAuthorawards(String Authorawards){
        this.Authorawards = Authorawards;
    }
    
    // get method to get the genre the author is may tailored to and writes about, genre is key to a customers taste, everyone has different tastes.
    public String getAuthorGenre(){
        return AuthorGenre;
    }
    
    // set method to set the Author's genre, a new book may need to be added to the system and may be new to the store.
    public void setAuthorGenre(String AuthorGenre){
        this.AuthorGenre = AuthorGenre;
    }
    
    // get method to get the Author's Nationality, this varaible would add a real world context to the author data.
    public String getAuthorNationality(){
        return AuthorNationality;
    }
    
    // set method to set the Author's Nationality the Nationality may need to be changed or a new author may need to be set a Nationality.
    public void setAuthorNationality(String AuthorNationality){
        this.AuthorNationality = AuthorNationality;
    }
    
}
