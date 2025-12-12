/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.bookstoreapi.resource;

// Importing my in memory datastore contains all in memory storage like customers,books, carts, and orders.
import com.mycompany.bookstoreapi.datastore.DataStore;

// for my custom exceptions for a cleaner more specific error handling.
import com.mycompany.bookstoreapi.exception.*;

// Importing all of the model classes like Book, Order and CartItem.
import com.mycompany.bookstoreapi.model.*; // I needed to import all models because when a customer places an order all these tie in together.
// Book for stock, CartItem whats in the cart and Order to create a final order for the customer.

// Importing JakartaRestful WebServices Packages
import jakarta.ws.rs.*; // Core for Jax-Rs annottations like @Path, @Get, @Post etc.
import jakarta.ws.rs.core.MediaType; // This is used to define the input and ouput formats like JSON.
import jakarta.ws.rs.core.Response; // And this allows me to build custom HTTP responses.

import java.util.ArrayList; // Allows me to create new lists for the carts and orders.
import java.util.Date; // This is to timstamp each order to the current date (the date today for excample)
import java.util.List; // And this is to handle lists of cart items and orders.


/**
 * This Class handles everything related to the customer's orders.
 * It allows a customer to place a new order, view all their pass orders,
 * fetch a specific order by the order ID. 
 * I also made sure to validate inputs and handle cases like out-of-stock books using custom exceptions.
 * @author Hamza Hassan W2044381
 */

@Path("/customers/{customerId}/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

    // This method allows the customer to place an ordered based on te items in their cart.
    // First it checks if the customer even exists, then if their cart even has items 
    // Then if all the books are even in stock for the customer to purhase.
    // If Customer cannot be found it throws a CustomerNotFoundException with a message
    // If the cart is empty and has no items in it, it throws a CartNotFoundException with a message.
    // And the books are out of stock it throws a OutOfStockException with a message.
    // However, once everything is validated, it will create a new order object and saves it to my datastore.
    @POST
    public Response placeOrder(@PathParam("customerId") int customerId) {
        // First, i check if this customer ID provided by the user exists in the system.
        // If it dosen't exist, i throw my custom CustomerNotFoundException message.
        if (!DataStore.customersAcc.containsKey(customerId)) {
            // My custom error response (CustomerNotFoundException) - ( Informs with a clear message that with this customer id inputeted by the user no customers were found.
            throw new CustomerNotFoundException("Sorry but this Customer was not found.");
        }

        // Then i get the customer's cart from the in memory database (datastore)
        List<CartItem> cart = DataStore.userCarts.get(customerId);
        
        //I have to check now if the cart is empty and even exists at all, 
        // If the cart dosen't, i throw my custom CartNotFoundException.
        if (cart == null || cart.isEmpty()) {
            // My custom error reponse (CartNotFoundException) - (letting the user know the cart is empty.
            throw new CartNotFoundException("No order can be placed with an empty cart, fill it up.");
        }

        // Now i loop trough every item in the cart to valdate the stock, need to see if the books the customer wants are instock.
        for (CartItem item : cart) {
            // I try to fetch every book from my hamzaBookShelf (in memory database (datastore)).
            Book book = DataStore.hamzaBookShelf.get(item.getBookId());
            
            // If the book dosen't esist or there isn't enough stock, i throw my custom OutOfStockException with a message.
            if (book == null || book.getBookStock() < item.getBookCount()) {
                // My custom error response (OutOfStockException) - (Letting the user know the book they want is out of stock but with a clear message)
                throw new OutOfStockException("Sorry but the Book with this ID " + item.getBookId() + " is completely out of stock.");
            }
            
            // however, if the book is in stock, based on the order i would reduce the quantity of the book to refelect the new order. This is to track and maintain harmony in stock.
            book.setBookStock(book.getBookStock() - item.getBookCount());
        }

        // I then create a Unique ID for the new order. (auto generated)
        int orderId = DataStore.hamzaOrderIdCounter.getAndIncrement();
        
        // Thne i build the new order object using the Customer ID, the cart items whats inside, and the data today.
        Order order = new Order(orderId, customerId, new ArrayList<>(cart), new Date());

        // I then store this order in my Final Orders map. So, if the customer has no past orders, it would create a list by deafult to track this.
        DataStore.Finalorders.computeIfAbsent(customerId, k -> new ArrayList<>()).add(order);
        
        // After the customer places the order, i remove the customer's cart since should now be empty.
        DataStore.userCarts.remove(customerId);
        
        // Lastly, i return a 201 Created response with the new order included in the response, to show the customer has placed the order successfully.
        return Response.status(Response.Status.CREATED).entity(order).build();
    }

    // This method would return all the orders for a specific customer, based on the customer ID.
    // if the customer has no orders, it would show the customer/users an empty list because there would be no orders.
    @GET
    public Response getAllOrders(@PathParam("customerId") int customerId) {
        // I first get all the orders for this customer from the Final orders map.
        // If no orders exist., it returns an empty list by default.
        List<Order> orderList = DataStore.Finalorders.getOrDefault(customerId, new ArrayList<>());
        
        // I would then return the list of orders with a 200 Ok reponse to show that it was successful.
        return Response.ok(orderList).build();
    }
    
    // Lastly, this method would return a specific order by using both the Order ID and Customer ID.
    // it would check if the customer even has orders, then would search for this based on both the Customer's ID and the Order ID
    @GET
    @Path("/{orderId}")
    public Response getOrder(@PathParam("customerId") int customerId,
                             @PathParam("orderId") int orderId) {
        // I start by getting the list of orders for the customer.
        List<Order> orderList = DataStore.Finalorders.get(customerId);
        
        // and if there are no orders found at all, i throw the exception CustomerNotFoundException with a message.
        if (orderList == null) {
            // My custom error response (CustomerNotFoundException) - (This is to show the user that there are no orders that were found for the customer that they have inputed).
            throw new CustomerNotFoundException("Sorry, but there are no orders that were found for this customer.");
        }
        
        // Then i go through each order to find the one with the matching ID provided.
        for (Order order : orderList) {
            if (order.getOrderId() == orderId) {
                return Response.ok(order).build();
            }
        }

        // if the loop finishes, and nothing is mathced, i throw the InvalidInputException with a message.
        // This mesage is to show to the user maybe the order number inputet is incorrect and cannot be linked to any customer.
        throw new InvalidInputException("This Order ID " + orderId + " was not found for any customer.");
    }
}
