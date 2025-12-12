/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstoreapi.model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Hamza Hassan W2044381.
 */
// This class is for the customer orders, after checking out their carts.
public class Order {
    private int orderId;
    private int customerId;
    private List<CartItem> orderedItems;
    private Date dateOrdered;
    private String paymentMethod;
    private String orderStatus;
    private String trackingNumber;
    private double orderTotal;
    
    // empty constructor, needed for when the API converts JSON into objects automatically.
    public Order() {}

    // full constructor to set everything in one go, like the order id, customerid, cart items and the date it was ordered.
    public Order(int orderId, int customerId, List<CartItem> orderedItems, Date dateOrdered, String paymentMethod, String orderStatus, String trackingNumber, double orderTotal) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderedItems = orderedItems;
        this.dateOrdered = dateOrdered;
        this.paymentMethod = ;
        this.orderStatus = orderStatus;
        this.trackingNumber = trackingNumber;
        this.orderTotal = orderTotal;
    }

    // get method to get the customer's order ID, this is the unique identifier that shows the order so the customer can be logged as they have made an order, which would link them to what they even purchased as well.
    public int getOrderId() { 
        return orderId; 
    }
    
    // set method to set the order Id. This is a unique identifier and may need to be assigned to a customer.
    public void setOrderId(int orderId) {
        this.orderId = orderId; 
    }

    // get method to get the Customer ID of the customer that ordered. This is mainly to identify the customer that placed the order.
    public int getCustomerId() { 
        return customerId; 
    }
    
    // updating/ setting the customer ID if needed to what customer made what order. This is key to link the correct order to the correct customer.
    public void setCustomerId(int customerId) { 
        this.customerId = customerId; 
    }
    
    // get method to get the items in the cart that were ordered. This is mainly to display what items were included in the order. This is useful for when sending confirmations and showing order history.
    public List<CartItem> getOrderedItems() { 
        return orderedItems; 
    }
    
    // set method to set the ordered items, this is mainly to store the exact lists of items that were in the cart when the order was placed.
    public void setOrderedItems(List<CartItem> orderedItems) {
        this.orderedItems = orderedItems; 
    }
    
    // get method to get the actual date the order was placed on. This is key as it's very important and links to the tracking and sorting of the orders by time.
    public Date getdateOrdered() {
        return dateOrdered; 
    }
    
    // set method to maybe set the date the order was made on. To record the exatc timestamp.
    public void setdateOrdered(Date dateOrdered) { 
        this.dateOrdered = dateOrdered;
    }
    
    // get method to get the customer's Payment Method to show and display how the customer paid for there order.
    public String getPaymentMethod(){
        return paymentMethod;
    }
    
    // set method to set the customer's Payment Method. This is to store how the customer paid for the order when they placed their order, it can be i dont know down the line used for confirmation emails or recipts.
    public void setPaymentMethod(String paymentMethod){
        this.paymentMethod = paymentMethod;
    }
    
    // get method to get the status of the customer's order. This is to show the customer the current state whether its processing, shipped or ddelivered. Customer's allways feel better when they know where there orders are.
    public String getOrderStatus(){
        return orderStatus;
    }
    
    // set method to set the customer's order status. This may need to be updated as it goes through the diffent stages of delivery like when shipped or delivered.
    public void setOrderStatus(String orderStatus){
        this.orderStatus = orderStatus;
    }
    
    // get method to get the customer's tracking number. Again, this is to give the tracking number to the customer so they can track their order and the shipment of it.
    public String getTrackingNumber(){
        return trackingNumber;
    }
    
    // set methdo to set the customer's tracking number, this is to link the tracking number to that specific order placed by the customer, so that it can be looked into by the customer for further updates or status checks.
    public void setTrackingNumber(String trackingNumber){
        this.trackingNumber = trackingNumber;
    }
    
    // get method to get the total of the order in terms of price. This is to show the customer how much they need to pay and it can be included in emails sent to them if need be and reciepts.
    public double getOrderTotal(){
        return orderTotal;
    }
    
    // set method to set the total of the order, this may be to calculate and mainly store the final price of the order (based on the cart items) so it dosen't need to be recalculated every time they may retreive the order, or return.
    public void setOrderTotal(double orderTotal){
        this.orderTotal = orderTotal;
    }
    
}
