package com.example.back_prueba_tecnica_civa.exception.custom;

public class NotDataFoundException extends RuntimeException {

    public NotDataFoundException() {
        super();
    }

    public NotDataFoundException(String message) {
        super(message);
    }
}
