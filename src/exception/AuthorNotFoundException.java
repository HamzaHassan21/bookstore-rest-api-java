/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstoreapi.exception;

/**
 *
 * @author Hamza Hassan W2044381
 */
// I made this custom exception so if someone looks for an author that dosen't exist,
// i can throw my own message instead of a boring system error.
public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(String message) {
        super(message);
    }
}
