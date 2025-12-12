/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.bookstoreapi.resource;

// grabbing my in memory data store that has all the Authors and Books
import com.mycompany.bookstoreapi.datastore.DataStore;

// these are my custom errors AuthorNotFound and InvalidInput, for handling missing authors and invalid fields.
import com.mycompany.bookstoreapi.exception.AuthorNotFoundException;
import com.mycompany.bookstoreapi.exception.InvalidInputException;

// importing both models Author and book.
import com.mycompany.bookstoreapi.model.Author; // This is need to allow me to create, manage and return the author objects in my API
import com.mycompany.bookstoreapi.model.Book; // This is needed for example when i need to check or return specific books which are tied in with author detials.

// Importing JakartaRestful WebServices Packages
import jakarta.ws.rs.*; // Core for Jax-Rs annottations like @Path, @Get, @Post etc.
import jakarta.ws.rs.core.MediaType; // This is used to define the input and ouput formats like JSON.
import jakarta.ws.rs.core.Response; // And this allows me to build custom HTTP responses.

import java.util.ArrayList; // Allows me to store and create a dynamic list so when for example i add in an author it grows as i add.
import java.util.List; // This is a general interface i used when returning author lists, so i'm tied to a specific type like array lists.
import java.util.stream.Collectors; // This helps me collect results from a stream back into a normal list after filtering authors.

/**
 * This class handles all RESTful operations related to managing the Authors in the System.
 * 
 * I used JAX-RS annotations like @Path, @POST, @GET, @PUT and @Delete to define different endpoints.
 * 
 * I also used @Produces and @Consumes to ensure the API communicates using JSON format.
 * 
 * Custom exceptions are used for cleaner error handling also so that it is a proper message instead of crashing the program.
 * 
 * @author Hamza Hassan W2044381
 */

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)


public class AuthorResource {
    
    // This method adds me to add basically create a new author using the data sent from Postman( Things like the name and biography etc).
    @POST
    public Response createAuthor(Author author) {
        // first i need to check if the name and bio are empty or null, and if they are it throws a InvalidInputException with a message.
        if (author.getAuthorName() == null || author.getAuthorBio() == null) {
            // My custom error response (InvalidInputException) - (The Author name and Author Biography is null so it hasn't been inputed).
            throw new InvalidInputException("The Author's name and biography are needed and required."); 
        }
          
        // assigning an ID to the new author using my counter. (auto generated)
        int id = DataStore.authorIdCounter.getAndIncrement();
        author.setAuthorId(id);
        
        // saving this new author in the in memory map.
        DataStore.authorRecords.put(id, author);
        
        // sending back the new author as JSON with a status of 201 to show it worked (A new Author is created).
        return Response.status(Response.Status.CREATED).entity(author).build();
    }

    // This method returns a list of all the authors that are stored in the system, (All of the Authors in the system).
    @GET
    public Response getAllAuthors() {
        return Response.ok(new ArrayList<>(DataStore.authorRecords.values())).build();
    }
  
    // And this method allows me to get a specific author based and using their author ID.
    @GET
    @Path("/{id}")
    public Response getAuthor(@PathParam("id") int id) {
        Author author = DataStore.authorRecords.get(id);
        
        // if the author dosen't exist, and is null it would show a AuthorNotFoundException to show this Author ID isn't assigned to an author and cannot be found (in a message).
        if (author == null) {
            // my custom error reponse (AuthorNotFoundException) - (The Author cannot be found because the Author ID cannot be found and dosen't exist).
            throw new AuthorNotFoundException("Opps! Sorry the Author with this ID " + id + " was not found."); 
        }
        // returns the author's information with a success reponse, to show the author was found.
        return Response.ok(author).build();
    }

    // this methods lets me update an author's details using thier Author ID.
    @PUT
    @Path("/{id}")
    public Response updateAuthor(@PathParam("id") int id, Author updated) {
        
        // this would check to see if the author exists before updating, and if the author dosen't exist it would throw a AuthorNotFoundException with a message.
        if (!DataStore.authorRecords.containsKey(id)) {
            // My custom error response (AuthorNotFoundException) - (The Author ID cannot be found, the author you want to update).
            throw new AuthorNotFoundException("Opps! Sorry the Author with this ID " + id + " was not found."); 
        }

        // setting the correct Author Id to the author and replaces the old data assigned to the Author.
        updated.setAuthorId(id);
        DataStore.authorRecords.put(id, updated);
        
        // this would send back the updated author with a 200 ok to confirm the update was successful.
        return Response.ok(updated).build();
    }

    // this method allows me to delete an author based and using the Author's ID.
    @DELETE
    @Path("/{id}")
    public Response deleteAuthor(@PathParam("id") int id) {
        // removes this author based on thier ID being null, and if it's not found i would throw a AuthorNotFoundException  with a message.
        if (DataStore.authorRecords.remove(id) == null) {
            // My custom error reponse (AuthorNotFoundException) - (This Author ID cannot be found and dosen't exist, an Author ID is needed to delete and Author).
            throw new AuthorNotFoundException("Opps! Sorry the Author with this ID " + id + " was not found."); 
        }
        // author is gone, so it owuld return 204 to show it was deleted completely fine, this is to see if the removal worked.
        return Response.noContent().build();
    }

    // this method will get all the books that were written by a specific author based on thier Author ID.
    @GET
    @Path("/{id}/books")
    public Response getBooksByAuthor(@PathParam("id") int id) {
        // before trying to find these books, i check if the author exists on the system first, if not i throw a AuthorNotFoundException with a message.
        if (!DataStore.authorRecords.containsKey(id)) {
            // My custom error response (AuthorNotFoundException) - ( The Author ID cannot be found therefore cannot be linked to any book).
            throw new AuthorNotFoundException("Opps Sorry the Author with this ID " + id + " was not found."); 
        }

        // here it's filtering all the books to match and find the author's ID.
        List<Book> authoredBooks = DataStore.hamzaBookShelf.values().stream()
                .filter(b -> b.getAuthorId() == id)
                .collect(Collectors.toList());

        // Lastly, it would return the list of books with a success reponse to confirm it worked.
        return Response.ok(authoredBooks).build();
    }
}

