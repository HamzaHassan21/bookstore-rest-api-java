# Bookstore REST API – Client–Server Architecture (Java, JAX-RS)
## Overview

This project implements a fully functional RESTful API for a fictional bookstore, developed using Java, JAX-RS (Jersey), and the Grizzly HTTP server.
All data is stored in-memory using HashMap and ArrayList, following the coursework requirement to avoid external databases or frameworks.

The API exposes JSON-based endpoints for managing authors, books, customers, shopping carts, and orders — demonstrating clear client–server design, resource modelling, validation, and exception handling.

## Core Functionality

The API supports full CRUD operations for:

Authors

Books

Customers

Shopping Carts

Orders

Each resource follows REST standards using meaningful HTTP methods, status codes, and structured JSON responses.

## Key Features

RESTful architecture using javax.ws.rs and Jersey

In-memory persistence with auto-incrementing identifiers

Custom exceptions, including:

AuthorNotFoundException

BookNotFoundException

CustomerNotFoundException

CartNotFoundException

InvalidInputException

OutOfStockException

Centralised error handling using ExceptionMapper for consistent JSON error output

Input validation with clear and descriptive error messages

Extensive inline documentation explaining all logic and design decisions

Postman-tested endpoints covering valid and invalid scenarios

## Project Structure
bookstore-rest-api-java/
│
├── src/    

│   ├── datastore/      # In-memory data stores

│   ├── exception/      # Custom exceptions + mappers

│   ├── main/           # Application entry point (Grizzly server)

│   ├── model/          # Java object models (Author, Book, etc.)

│   └── resource/       # REST endpoint classes
│
├── report/

│   └── Bookstore_REST_API_Test_Report.pdf
│
└── README.md

## How to Run the Application

Ensure Java 17+ is installed.

Clone or extract the project.

Open in NetBeans, IntelliJ, VS Code, or any IDE supporting Java.

Build the project:

mvn clean install


Run the application by executing Main.java.

The API will be available at:

http://localhost:8080/api/


You can now interact with the API using Postman, cURL, or any REST client.

## Testing Summary

A full test report is included in the /report directory.
It contains:

Endpoint-by-endpoint validation

Screenshots of successful requests

Tests for invalid inputs

Expected vs. actual HTTP status codes

Demonstrations of custom exception handling

e.g., “Book with ID 99 not found”

The testing process verifies predictable and stable behaviour across all CRUD operations.

## Demo Video

A complete walkthrough — including code explanation, endpoint behaviour, and Postman testing — is available here:

YouTube Demo:
(https://www.youtube.com/watch?v=RR-QPvmMjh8)

## Notes

This project was built from scratch with a strong focus on:

Clean and modular code

Clear resource separation

Realistic REST API behaviour

Easily readable documentation

Robust and transparent error handling

It serves as a demonstration of Java-based API development, exception management, and client–server architectural principles.

