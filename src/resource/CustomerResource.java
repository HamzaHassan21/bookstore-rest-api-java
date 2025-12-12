/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstoreapi.resource;

// grabbing my in memory data store that has all the Customers.
import com.mycompany.bookstoreapi.datastore.DataStore;

// These are the Exception classes, so i can give a proper feedback if an exception or error happens.
import com.mycompany.bookstoreapi.exception.CustomerNotFoundException; // This exception occurs when a Customer is not found.
import com.mycompany.bookstoreapi.exception.InvalidInputException; // This exception happens when an invalid input is given by the user.

// I imported this model to allow me to create, update and return customer objects in all the endpoints.
import com.mycompany.bookstoreapi.model.Customer;

// Importing JakartaRestful WebServices Packages
import jakarta.ws.rs.*; // Core for Jax-Rs annottations like @Path, @Get, @Post etc.
import jakarta.ws.rs.core.MediaType; // This is used to define the input and ouput formats like JSON.
import jakarta.ws.rs.core.Response; // And this allows me to build custom HTTP responses.

// This is used to return a list of all customers and also, due to dynamic, so when you would want to delete or add customers.
import java.util.ArrayList;



/**
 * * This class handles all operations related to customers in my Bookstore API.
 * I used standard JAX-RS annotations to map HTTP requests to Java methods.
 * The API supports creating, retrieving, updating, and deleting customers.
 * All responses are in JSON format, and I used custom exceptions to make the error handling more readable.
 * 
 * @author Hamza Hassan W2044381
 */

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {

    // This method is used to create a new Customer Account basically
    // it checks if the required fields are present before creating the account,
    // Things like customer name, email, password.
    @POST
    public Response createCustomer(Customer customer) {
        // First, i'm checking that all the needed fields are filled in by the user.
        // if name, email and password any of them are mission, i throw a InvalidInputException with a message.
        if (customer.getCustomerName() == null || customer.getCustomerEmail() == null || customer.getCustomerPassword() == null) {
            // My Custom error response (InvalidInputException) (informs the user that the fields need to be filled in).
            throw new InvalidInputException("All the customer fields are required to be filled in.");
        }

        // i use this method to generate a new unique customer id, maybe i might have a new customer that hasn't got an ID.
        int id = DataStore.accountCounter.getAndIncrement();
        
        // Then i assign that ID to the new customer object.
        customer.setCustomerId(id);
        
        // Then i add the new customer to my in memory customer map (datastore).
        DataStore.customersAcc.put(id, customer);
        
        // Lastly, I return a 201 created response along with the customer that was created.
        return Response.status(Response.Status.CREATED).entity(customer).build();
    }

    // This method returns all the customers that are in the whole system.
    // it wraps the values in a new arraylist and returns them as a JSON array.
    @GET
    public Response getAllCustomers() {
        // I convert the map values (so which are all the customer objects) into a list.
        // and then i return them with a 200 okay status.
        return Response.ok(new ArrayList<>(DataStore.customersAcc.values())).build();
    }

    // This method can get a single customer from the whole system just based on the customer ID.
    // And if the customer is not found, a CustomerNotFoundException is thrown with a message.
    @GET
    @Path("/{id}")
    public Response getCustomerById(@PathParam("id") int id) {
        // I try to fetch the customer from the map (Datstore) using their ID.
        Customer customer = DataStore.customersAcc.get(id);
        
        // if the customer is not found or dosent exists i throw a CustomerNotFoundException with a message for clarity to the user.
        if (customer == null) {
            // My custom error response (CustomerNotFoundException) - (gives a message saying this customer with this givn ID cannot be found.
            throw new CustomerNotFoundException("Sorry, but this Customer with ID " + id + " was not found.");
        }
        
        // if the customer does exists however, i would return the customer with a 200 OK response to show it was successful.
        return Response.ok(customer).build();
    }

    // Now, this method updates the details of an existing customer.
    // it first checks if the customer exists, if not it throws a CustomerNotFoundException with a message.
    // Then it would update the record with the new data.
    @PUT
    @Path("/{id}")
    public Response updateCustomer(@PathParam("id") int id, Customer updated) {
        // i'm checking if the customer with the given Customer Id exists in the system.
        if (!DataStore.customersAcc.containsKey(id)) {
            // My custom error response (CustomerNotFoundException) - (Gives a message saing that this customer cannot be found based on the the Customer ID provided.
            throw new CustomerNotFoundException("Sorry, but this Customer with ID " + id + " was not found.");
        }

        // Then i reassign the ID to make sure the updated object keeps the same ID as the one its actually replacing.
        updated.setCustomerId(id);
        
        // Then i replace the old customer object with the new one in the map (Datastore).
        DataStore.customersAcc.put(id, updated);
        
        // Then i return the updated customer with a 200 OK status to show it was successful, the Customer details has been udpated
        return Response.ok(updated).build();
    }

    // This method deletes a customer from the system using the Customer's ID.
    // However, if the customer dosen't exist, a CustomerNotFoundException is thrown with a message for clarity to the user.
    @DELETE
    @Path("/{id}")
    public Response deleteCustomer(@PathParam("id") int id) {
        // i try to remove the customer using their ID.
        // if remove returns as null, that tells me that there is  no customer that was found with that ID, and it throws a CustomerNotFoundException with a message.
        if (DataStore.customersAcc.remove(id) == null) {
            // My custom error reponse (CustomerNotFoundException) - (tells the user that this specific customer id cannot be linked to any customers in the system, maybe its a invalid input or no input at all.
            throw new CustomerNotFoundException("Sorry, But this Customer with ID " + id + " was not found.");
        }
        
        // I return a 204 no content reponse to confirm that the deletion of the customer was successful and worked.
        return Response.noContent().build();
    }
}
