package com.example.back_prueba_tecnica_civa.exception.custom;

import java.util.List;

public class BadRequestException extends RuntimeException {

    private final List<String> badFields;

    public BadRequestException(String message, List<String> badFields) {
        super(message);
        this.badFields = badFields;
    }

    public List<String> getBadFields() {
        return badFields;
    }

}
