/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstoreapi.model;

/**
 *
 * @author Hamza Hassan W2044381
 */

// this class represents one item added to the cart (e.g 5 copies of a book that is bookId- 3).
public class CartItem {
    private int bookId;
    private int bookCount;
    private String bookTitle;
    private double bookPrice;
    private double cartSubTotal;

    //empty constructor, needed for when the API converts JSON into objects automatically.
    public CartItem() {}

    // constructor to create a cart item quickly passing both the books id and count.
    public CartItem(int bookId, int bookCount, String bookTitle, double bookPrice, double cartSubTotal) {
        this.bookId = bookId;
        this.bookCount = bookCount;
        this.bookTitle = bookTitle;
        this.bookPrice = bookPrice;
        this.cartSubTotal = cartSubTotal;
    }

    // get method to get the book's ID of the books in the cart.
    public int getBookId() {
        return bookId;
    }

    // set method to set the book's ID/ maybe would need to update the book id of the books in the cart.
    public void setBookId (int bookId) {
        this.bookId = bookId;
    }

    // get method to get the book count (the amount/quantity of books), the amount of books or items in the cart.
    public int getBookCount() {
        return bookCount;
    }

    // set method to set the book count (amount quantity of books), the amount of books or items in the cart.
    public void setBookCount(int bookCount) {
        this.bookCount = bookCount;
    }
    
    // get method to get the book's title, this is so it can display on the cart details so the customer can see and verify what they even added to the cart.
    public String getbookTitle(){
        return bookTitle;
    }
    
    // set method to set the booktitle, a book title may need updating so the customer can purchase what they think there purchasing. This is so the cart details are accurate.
    public void setbookTitle(String bookTitle){
        this.bookTitle = bookTitle;
    }
    
    // get method to get the book's price so that it can be displayed in the cart to allow the customer to double check and see what they added to the cart.
    public double getbookPrice(){
        return bookPrice;
    }
    
    // set method to set a book's price. There may be a time where the price needs to be updated and changed.
    public void setbookPrice(double bookPrice){
        this.bookPrice = bookPrice;
    }
    
    // get method to get the sub total of the cart. This is to allow the customer to keep track on their spend and shows them how much the cart has already accumulated with the items they have added already.
    public double getcartSubTotal(){
        return cartSubTotal;
    }
    
    // set method to set the cart's sub total. This is to easily calculate and return the total cost for each item in the cart totally. This saves time, and can improve the performance on purchase for a customer.
    public void setCartSubTotal(double cartSubTotal){
        this.cartSubTotal = cartSubTotal;
    }
    
}