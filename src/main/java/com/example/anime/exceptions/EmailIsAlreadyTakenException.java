package com.example.anime.exceptions;

public class EmailIsAlreadyTakenException extends RuntimeException {

    public EmailIsAlreadyTakenException() {
    }

    public EmailIsAlreadyTakenException(String message) {
        super(message);
    }
}
