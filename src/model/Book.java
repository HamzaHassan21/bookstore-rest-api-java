/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstoreapi.model;

/**
 *
 * @author Hamza Hassan W2044381.
 */

//This is the Book class that would hold the information about each book in the store.
public class Book {
    private int bookId;
    private int authorId;
    private String Booktitle;
    private String bookIsbn;
    private int publicationYear;
    private String BookLanguage;
    private double Bookprice;
    private int Bookstock;
    private double BookRating;

    // empty constructor, needed for when the API converts JSON into objects automatically.
    public Book() {}

    // Only constructor i done included and will pass through all the variables.
    public Book(int bookId, int authorId, String Booktitle, String bookIsbn, int publicationYear, String BookLanguage, double Bookprice, int Bookstock, double BookRating) {
        this.bookId = bookId;
        this.authorId = authorId;
        this.Booktitle = Booktitle;
        this.bookIsbn = bookIsbn;
        this.publicationYear = publicationYear;
        this.BookLanguage = BookLanguage;
        this.Bookprice = Bookprice;
        this.Bookstock = Bookstock;
        this.BookRating = BookRating;
       
    }
    
    // get method to get the book's ID, each book is assigned to an id so its key that i get the books id.
    public int getBookId() { 
        return bookId; 
    }
    
    // set method to set the book's ID, might need to update the books id if any issues come down the line like misplacements or confusion.
    public void setBookId(int bookId) {
        this.bookId = bookId; 
    }
    
    // get method to get the Author's ID, the customer might want the authors name just so that they are informed.
    public int getAuthorId() { 
        return authorId;
    }
    
    // set method to set the Author's ID, might need to update the authors id.
    public void setAuthorId(int authorId)
    { this.authorId = authorId;
    
    }
    
    // get method to get the Book's Title - this for clarity so the customer can actually see the book they'll be purchasing.
    public String getBookTitle() { 
        return Booktitle; 
    }
    
    // set method to set the Book's Title, allows me to be able to update or assign a title to the book.
    public void setBookTitle(String Booktitle) { 
        this.Booktitle = Booktitle; 
    }
    
    // get method to get the book's ISBN (Serial Number) - key for identifying which edition of the book or what type of book the customer wants and is ordering.
    public String getbookIsbn() { 
        return bookIsbn; 
    }
    
    // set method to set the book's ISBN (Serial Number), lets me assign or update the ISBN of the book.
    public void setbookIsbn(String bookIsbn) { 
        this.bookIsbn = bookIsbn; 
    }
    
    // get method to get the book's Publication Year (Year it was published), information for the customer.
    public int getPublicationYear() { 
        return publicationYear; 
    }
    
    // set method to set the book's Publication Year (Year it was published), might need to update or might be a fault in original Publication year of the book/item.
    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear; 
    }
    
    // get method to get the book's Language, what language was the book published and written in. This is key info for a customer.
    public String getBookLanguage(){
        return BookLanguage;
    }
    
    // set method to set the book's language, may need to update the book's language or it may be a new book that needs to be set on what language it is. 
    public void setBookLanguage(String BookLanguage){
        this.BookLanguage = BookLanguage;
    }

    // get method to get the price of the book, needed for when order is placed to let customer know how much the book/item actually is.
    public double getBookPrice() {
        return Bookprice; 
    }
    
    // set method to set the price of the book, the price might need updating due to inflation or store price changes.
    public void setBookPrice(double Bookprice) {
        this.Bookprice = Bookprice; 
    }

    // get method to get the stock number of the book, this is vital, need to check and get the book stock to see avaliablity.
    public int getBookStock() {
        return Bookstock; 
    }
    
    // set method to set the stock number of the book, maybe would need to update the stock numbers.
    public void setBookStock(int stock) {
        this.Bookstock = stock; 
    }
    
    // get method to get the rating of the book, these ratings may be done by the customers.
    public double getBookRating(){
        return BookRating;
    }
    
    // set method this is to set the rating of the book, may need to calculate all the book ratings and average it out for the customers to know.
    public void setBookRating(double BookRating){
        this.BookRating = BookRating;
    }
}
