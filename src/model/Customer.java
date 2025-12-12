/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstoreapi.model;

/**
 *
 * @author Hamza Hassan W2044381.
 */

// This class is for the customers that are purchasing books.
public class Customer {
    private int customerId;
    private String customerName;
    private String gender;
    private String customerEmail;
    private String customerPassword;
    private String customerPhoneNumber;
    private String billingAddress;
    private String shippingAddress;

    // empty constructor, needed for when the API converts JSON into objects automatically.
    public Customer() {}

    // this constructor creates a ful customer quickly, includes all the variables.
    public Customer(int customerId, String customerName, String gender, String customerEmail, String customerPassword, String customerPhoneNumber, String billingAddress, String shippingAddress) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.gender = gender;
        this.customerEmail = customerEmail;
        this.customerPassword = customerPassword;
        this.customerPhoneNumber = customerPhoneNumber;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
    }

    // get method to get the customer's ID so i can find and get it later on.
    public int getCustomerId() {
        return customerId;
    }

    // set method to set the customer's ID, might need to add in a new customer so i need to be able to set a new Customer ID.
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    // get method to get the customer's name, this is key to show and confirm who the customer is.
    public String getCustomerName() {
        return customerName;
    }

    // set method to set the customer's name, might need to update the customers Name, due to changes or preferences.
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    // get method to get the customer's gender, this may be done for security reasons or to verify a customer.
    public String getGender(){
        return gender;
    }
    
    // set method to set a customer's gender, this could be when a customer is registaring with us and may need to give their gender.
    public void setGender(String gender){
        this.gender = gender;
    }
    
    // get method to get the customer's email, need to get the customers email maybe for contact or they may use it to log into the store.
    public String getCustomerEmail() {
        return customerEmail;
    }

    // set method to set the customer's email, this could be updated due to changes or the customer may just want to change their email.
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    // get method to get the customer's password, this would be internal due to security risk but it's to confirm te user's log in password matches and is correct.
    public String getCustomerPassword() {
        return customerPassword;
    }

    // set method to set the customer's password/ maybe i would need to update the customer's password based on the customer's request.
    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }
    
    // get method to get the customer's phone number, this gives the store a option of contact and to send the customer's order status to.
    public String getcustomerPhoneNumber(){
        return customerPhoneNumber;
    }
    
    // set method to set the customer's phone number, this may need to be set or updated. Either a new customer needs to be registered and set, or a customer may change phone number and may need it to be updated.
    public void setcustomerPhoneNumber(String customerPhoneNumber){
        this.customerPhoneNumber = customerPhoneNumber;
    }
    
    // get method to get the customer's billing address, this is needed to verify a customer's identity and ensure the payment is legitimate, preventing fradulent transactions.
    public String getBillingAddress(){
        return billingAddress;
    }
    
    // set method to set the customer's billing adress. This may need to be set and logges for security purposes as explained above when i get billing address.
    public void setBillingAdress(String billingAddress){
        this.billingAddress = billingAddress;
    }
    
    // get method to get the customer's shipping address. This is required to have logged into the system as this is the address wehre the items/books may be shipped to.
    public String getShippingAddress(){
        return shippingAddress;
    }
    
    // set method to set the customer's shipping address. This may need to be updated based on customer request.
    public void setShippingAdress(String shippingAddress){
        this.shippingAddress = shippingAddress;
    }
    
    
}
