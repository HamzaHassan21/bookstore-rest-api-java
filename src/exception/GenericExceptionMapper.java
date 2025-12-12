/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstoreapi.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.WebApplicationException;

import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author Hamza Hassan W2044381.
 */

@Provider // this lets the Jersey know im using this class to handle all exceptions in one place.

// I added this class to handle all the custom expceptons i made.
// instead of showing normal system errors, i want to send back much cleaner reponses.
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {
    
    
    @Override
    public Response toResponse(Throwable ex) {
        int status = 500;  // default to 500 in case it's a random/unexpectored error.

        // if so if its a built in web exception like 404 or 405, i just return the same status.
        if (ex instanceof WebApplicationException)
            status = ((WebApplicationException) ex).getResponse().getStatus();
        
        // these are all my not found errors; for book, author, cart and customer.
        // i group them gere so they all return 405.
        else if (ex instanceof BookNotFoundException || ex instanceof AuthorNotFoundException ||
                 ex instanceof CustomerNotFoundException || ex instanceof CartNotFoundException)
            status = 404;
        
        // these are more like bad inputs or logic errors (invalid values).
        else if (ex instanceof InvalidInputException || ex instanceof OutOfStockException)
            status = 400;

        // this part builds a clean error message as JSON (with the error type with the message itself).
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getClass().getSimpleName()); // tells me which error was trigerred, (e.g. BookNotFoundException).
        error.put("message", ex.getMessage()); // whatever message i wrote when i threw it

        return Response.status(status).entity(error).build();
    }
}
