package com.kodilla.library.exceptions;

public class BookCopyNotFoundException extends RuntimeException {
    public BookCopyNotFoundException() {}
    public BookCopyNotFoundException(String message) {
        super(message);
    }
}
