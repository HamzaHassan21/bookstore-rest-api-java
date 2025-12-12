/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.bookstoreapi.resource;

// grabbing my in memory data store that has all the Books and CartItems.
import com.mycompany.bookstoreapi.datastore.DataStore;

// These are the Exception classes, so i can give a proper feedback if an exception or error happens.
import com.mycompany.bookstoreapi.exception.BookNotFoundException; // This exception is for when a Book isn't found in the system.
import com.mycompany.bookstoreapi.exception.CartNotFoundException; // This exception is for when a Cart isn't found.
import com.mycompany.bookstoreapi.exception.CustomerNotFoundException; // This exception is for when a customer cannot be found.
import com.mycompany.bookstoreapi.exception.OutOfStockException; // This is exception is when a specific item or book is out of stock and not avaibale for the customers to putchase.

// importing both models Book and CartItem. 
import com.mycompany.bookstoreapi.model.Book; // Needed to get details like the Book ID, Stock so for when cart items are updated and adding to the carts.
import com.mycompany.bookstoreapi.model.CartItem; // This represents every item in the cart, e.g book ID and count (quantity).

// Importing JakartaRestful WebServices Packages
import jakarta.ws.rs.*; // Core for Jax-Rs annottations like @Path, @Get, @Post etc.
import jakarta.ws.rs.core.MediaType; // This is used to define the input and ouput formats like JSON.
import jakarta.ws.rs.core.Response; // And this allows me to build custom HTTP responses.

import java.util.ArrayList; // Used arrays lists because as customer's add more items the list needs to be dynamic to adapt and grow based on requests made by the customers.
import java.util.List; // I used this for method parameters and return values when deaing with the carts.


/**
 * This class handles all RESTful operations related to managing a customer's cart.
 * 
 * I used JAX-RS annotations like @Path, @POST, @GET, @PUT and @Delete to define different endpoints.
 * 
 * I also used @Produces and @Consumes to ensure the API communicates using JSON format.
 * 
 * Custom exceptions are used for cleaner error handling also so that it is a proper message instead of crashing the program.
 * 
 * @author Hamza Hassan W2044381
 */

@Path("/customers/{customerId}/cart")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartResource {

    // This method allows me to add a new item to the cart using the Customer ID and Cart item (which includes bookid and book count (quantity)).
    @POST
    @Path("items")
    public Response addItemToCart(@PathParam("customerId") int customerId, CartItem item) {
        // first it checks if the customer exists based on the cutomer's ID, if not it will throws a CustomerNotFoundException with a message.
        if (!DataStore.customersAcc.containsKey(customerId)) {
            // My custom error reponse (CustomerNotFoundException) - (shows that the cannot find the customer based on the customer id).
            throw new CustomerNotFoundException("Sorry, but this Customer cannot be found based on the Customer ID you have provided.");
        }
        

        // Then it checks the book can be found based on the book id, and if it can't it would produce a BookNotFoundException with a message.
        Book book = DataStore.hamzaBookShelf.get(item.getBookId());
        if (book == null) {
            // My custom error response (BookNotFoundException) - (The book cannot be found based on the book id provided).
            throw new BookNotFoundException("Opps! Sorry this Book cannot be found.");
        }

        // Lastlt, it checks the book stock so if the book is in stock and can be purchased and added to the customers cart, if not it throws a OutOfStockException with a message.
        if (item.getBookCount() > book.getBookStock()) {
            // My custom error response (OutOfStockException) - (This tells the customer that the book they want is out of stock or sold out).
            throw new OutOfStockException("Sorry, this book is sold out and there is no stock.");
        }
        
       // ensures the customer has a cart, if the customer dosen't it would create an empty cart for them.
        DataStore.userCarts.putIfAbsent(customerId, new ArrayList<>());
        
        // this would retrive the customer's cart from the datastore.
        List<CartItem> cart = DataStore.userCarts.get(customerId);

        // This is to deal with duplicates, so if the book already exists in the cart it will remove the old entry made.
        cart.removeIf(ci -> ci.getBookId() == item.getBookId());
        
        // Then this would add the new updated cart item to the cart.
        cart.add(item);

        // finally, it will return a 201 created response with the item that was added to show it was successful.
        return Response.status(Response.Status.CREATED).entity(item).build();
    }

    // This method will produce the contents of the customer's cart using their ID.
    @GET
    public Response viewCart(@PathParam("customerId") int customerId) {
        //first it checks if the customer exists in the system based on the customer id, if not it throws an CartNotFoundException with a message.
        if (!DataStore.customersAcc.containsKey(customerId)) {
            // My custom error reponse (CartNotFoundException) - (The customer cannot be found).
            throw new CustomerNotFoundException("Sorry, Based on the Customer ID, this Customer cannot be found.");
        }

        // Now, if the customer exists it gets their cart which is stored in the datastore.
        // if the cart dosen't exist it will return an empty list by defualt.
        List<CartItem> cart = DataStore.userCarts.getOrDefault(customerId, new ArrayList<>());
        
        // Finally, it will return the cart contents with a 200 ok reponse to show it has been successful you can view the cart.
        return Response.ok(cart).build();
    }

    // This method is to update a speciifc item in the customer's cart, using the customer id and book id to locate the exact cart and item.
    @PUT
    @Path("items/{bookId}")
    public Response updateCartItem(@PathParam("customerId") int customerId,
                                   @PathParam("bookId") int bookId,
                                   CartItem updated) {
        
        // First i try to get the customer's cart using the customer id.
        List<CartItem> cart = DataStore.userCarts.get(customerId);
        //if the cart dosen't exist in the system i will throw a CartNotFoundException with a message.
        if (cart == null) {
            // My custom error response (CartNotFoundException) - (Informing the customer that this cart cannot be found based on the customer id)
            throw new CartNotFoundException("Sorry but this Cart cannot be found.");
        }

        // Then i check if the book the user wants to update already exists in the cart
        // if it does, it removes the old version of that book to avoid duplicates.
        cart.removeIf(ci -> ci.getBookId() == bookId);
        
        // After removing the old item, i add the updated CartItem to the cart.
        // This updated item will reflect the new quantity or any other changes the user done.
        cart.add(updated);

        // Finally, i return a 200 ok reponse with the updated items from the cart.
        return Response.ok(updated).build();
    }

    // This Method is made to delete a specifc item from the customer's cart using the book ID.
    // it first checks if the cart exists for the given customer.
    // if it does the item with the matc
    @DELETE
    @Path("items/{bookId}")
    public Response deleteCartItem(@PathParam("customerId") int customerId,
                                   @PathParam("bookId") int bookId) {
        
        // First, i get the cart that belongs to the specific customer based on customer id
        // if the customer dosen't have a cart, the value will be null.
        List<CartItem> cart = DataStore.userCarts.get(customerId);
        
        // if no cart is found for the customer, i throw a CartNotFoundException with a message.
        if (cart == null) {
            // Custom error reponse (CartNotFoundException)- (The cart cannot be found)
            throw new CartNotFoundException("This Cart cannot be found.");
        }

        // Then i use removeIf to go through each item in the cart.
        // if any item matches the given BookId, it will be removed from the list.
        cart.removeIf(item -> item.getBookId() == bookId);
        
        // Finally, i return a 204 No Content to let the customer know the items were successfully deleted.
        return Response.noContent().build();
    }
}
