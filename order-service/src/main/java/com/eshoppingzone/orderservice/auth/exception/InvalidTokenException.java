package com.eshoppingzone.orderservice.auth.exception;



public class InvalidTokenException extends RuntimeException{
    public InvalidTokenException() {
        super("Invalid Token");
    }
}
