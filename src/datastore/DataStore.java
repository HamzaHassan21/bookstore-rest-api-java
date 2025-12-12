/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstoreapi.datastore;

// Here I'm Importing all the models i need for the data storage
import com.mycompany.bookstoreapi.model.Book;
import com.mycompany.bookstoreapi.model.Author;
import com.mycompany.bookstoreapi.model.Customer;
import com.mycompany.bookstoreapi.model.CartItem;
import com.mycompany.bookstoreapi.model.Order;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This Class would hold all the data like books, authors, customers, etc
 * Everything is static so it's shared across my project
 * @author Hamza Hassan W2044381
 */
public class DataStore {
    
    //storing all the books using thier ID
    public static Map<Integer, Book> hamzaBookShelf = new HashMap<>();
    
    // saving all the authors by their ID / Records
    public static Map<Integer, Author> authorRecords = new HashMap<>();
    
    // the customers information is stored with their ID (Account)
    public static Map<Integer, Customer> customersAcc = new HashMap<>();
    
    // here i'm trying to keep track of what each customers add to their cart
    public static Map<Integer, List<CartItem>> userCarts = new HashMap<>();
    
    // and the orders that were placed by the customers are tracked as well
    public static Map<Integer, List<Order>> Finalorders = new HashMap<>();

    // these are just counters i'm using to auto create IDs for stuff like books and orders (start from 1)
    public static AtomicInteger bookIdCounter = new AtomicInteger(1);
    public static AtomicInteger authorIdCounter = new AtomicInteger(1);
    public static AtomicInteger accountCounter = new AtomicInteger(1);
    public static AtomicInteger hamzaOrderIdCounter = new AtomicInteger(1);
}

