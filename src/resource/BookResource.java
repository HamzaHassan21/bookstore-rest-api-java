/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.bookstoreapi.resource;

// grabbing my in memory data store that has all the Books
import com.mycompany.bookstoreapi.datastore.DataStore;

// These are the Exception classes, so i can give a proper feedback if an exception or error happens.
import com.mycompany.bookstoreapi.exception.AuthorNotFoundException; // This is an exception when an author isn't found in the system.
import com.mycompany.bookstoreapi.exception.BookNotFoundException;  // This is an exception when a book isnt found in the system.
import com.mycompany.bookstoreapi.exception.InvalidInputException; // This is an exception when the user inputs something invalid, even null can be an invalid input.

// i'm importing my book model, so i can use it to add, update, and return book data in the API.
import com.mycompany.bookstoreapi.model.Book;

// Importing JakartaRestful WebServices Packages
import jakarta.ws.rs.*; // Core for Jax-Rs annottations like @Path, @Get, @Post etc.
import jakarta.ws.rs.core.MediaType; // This is used to define the input and ouput formats like JSON.
import jakarta.ws.rs.core.Response; // And this allows me to build custom HTTP responses.


// just using a basic list function to handle this collection of books.
import java.util.ArrayList; // I used this to create a list of books, so i can store and return multiple books at once.
import java.util.Calendar; // I used this to help manage dates, like for example the publication year of the books.

/**
 * This class handles all RESTful operations related to managing the books in the system.
 * 
 * I used JAX-RS annotations like @Path, @POST, @GET, @PUT and @Delete to define different endpoints.
 * 
 * I also used @Produces and @Consumes to ensure the API communicates using JSON format.
 * 
 * Custom exceptions are used for cleaner error handling also so that it is a proper message instead of crashing the program.
 * 
 * @author Hamza Hassan W2044381
 */

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {

    // This method allows me to add a new book to the system using data from Postman (Creation of a book).
    @POST
    public Response createBook(Book book) {
        // before i need to check if the book has any missing fields, like the title is null or the book's ISBN is null, if so it would throw a InvalidInputException with a message.
        if (book.getBookTitle() == null || book.getbookIsbn() == null ||
            book.getPublicationYear() > Calendar.getInstance().get(Calendar.YEAR)) {
            // my custom error response (InvalidInputException) - (the book details are invalid)
            throw new InvalidInputException("Sorry but these details provided are invalid!"); 
        }

        // then i'm checking if the Author Id actually exists - otherwise the book would link to nothing. If not found it will throw a AuthorNotFoundException with a message.
        if (!DataStore.authorRecords.containsKey(book.getAuthorId())) {
            // my custom error response (AuthorNotFoundException) - (the author ID dosen't exist and cannot be found).
            throw new AuthorNotFoundException("Opps! Sorry the Author with this ID " + book.getAuthorId() + " does not exist."); 
        }

        // then i assign a new book ID using my auto counter (this is auto generated).
        int id = DataStore.bookIdCounter.getAndIncrement();
        book.setBookId(id);
        
        // then this book would be saved in my map (acts like a database).
        DataStore.hamzaBookShelf.put(id, book);
        
        // then it would return the new book info with 201 to confirm this new book was created successfully.
        return Response.status(Response.Status.CREATED).entity(book).build();
    }

    // This method allows me to return and get all the books that are in the system.
    @GET
    public Response getAllBooks() {
        return Response.ok(new ArrayList<>(DataStore.hamzaBookShelf.values())).build();
    }

    // This method allows me to get a specifc book from the datastore using the book's ID.
    @GET
    @Path("/{id}")
    public Response getBookById(@PathParam("id") int id) {
        Book book = DataStore.hamzaBookShelf.get(id);
        
        // first i need to check if the book is null, if the book cannot be found with the ID, it will throw a BookNotFoundException with a message.
        if (book == null) {
            // my custom error response (BookNotFoundException) - (So the book cannot be found based on the fact the book's ID cannot be found.
            throw new BookNotFoundException("Opps! Sorry the Book with ID " + id + " was not found.");
        }
        
        // this would return the book details and information with a success reponse to show this specific book was found and brought.
        return Response.ok(book).build();
    }

    // this method allows me to update a book's information using the book's ID.
    @PUT
    @Path("/{id}")
    public Response updateBook(@PathParam("id") int id, Book updatedBook) {
        
        // first i need to check if the book actually exists, the Book ID exists, before trying to update anything, 
        //if it dosen't exist it will throw a BookNotFoundException with a message.
        if (!DataStore.hamzaBookShelf.containsKey(id)) {
            // My custom error response (BookNotFoundException) - (The book ID cannot be found therefore the book cannot be updated, this is needed basically)
            throw new BookNotFoundException("Opps! Sorry the Book with ID " + id + " was not found.");
        }

        // here i'm setting the correct Book Id and it would save this updated information in my map (datastore).
        updatedBook.setBookId(id);
        DataStore.hamzaBookShelf.put(id, updatedBook);
        
        // it would send back the updated book details with a 200 ok reponse to show it was successful.
        return Response.ok(updatedBook).build();
    }

    // This method allows me to delete a book from the system based on the book's ID.
    @DELETE
    @Path("/{id}")
    public Response deleteBook(@PathParam("id") int id) {
        // befofre trying to remove or delete a book i need to check if the Book ID is null and that specifc book can be found.
        // if the book cannot be found it will throw a BookNotFoundException with a message.
        if (DataStore.hamzaBookShelf.remove(id) == null) {
            // My customer error response (BookNotFoundException) - (The book' id cannot be found, and a book cannot be deleted without the book id).
            throw new BookNotFoundException("Opps! Sorry the Book with ID " + id + " was not found.");
        }
        
        // returns that the book has been delete successfully.
        return Response.noContent().build();
    }
}
