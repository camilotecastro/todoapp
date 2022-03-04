package com.co.todoapp.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class TodoExceptions extends RuntimeException{
    private String message;
    private HttpStatus httpStatus;
    public TodoExceptions(String message, HttpStatus httpStatus){
        super();
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
