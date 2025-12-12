/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstoreapi.main;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import java.io.IOException;
import java.net.URI;

/**
 *
 * @author Hamza Hassan W2044381
 */

public class Main {
    // this is the base URL where my API will run from
    public static final String BASE_URI = "http://localhost:8080/api/";
    
    // this method starts the server and connects my resource and exception packages
    public static HttpServer startServer() {
        // setting up the server with my API resource classes and exception handler
        final ResourceConfig rc = new ResourceConfig().packages(
            "com.mycompany.bookstoreapi.resource",  // all my API endpoints are in here
            "com.mycompany.bookstoreapi.exception"  // this handles all the custom errors I made
        );
        
         // this creates and starts the server using Grizzly
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) throws IOException {
        // starting the server so I can test it through Postman
        final HttpServer server = startServer();
         // just letting myself know the server has started through an output message
        System.out.println("The Server is running now at: " + BASE_URI);
        System.out.println("Please press Enter if you wish to stop...");
        
        // waits for me to hit Enter to stop the server (so it stays live during the testing)
        System.in.read();
        
        // shuts the server down properly when I finish
        server.shutdownNow();
    }
}


