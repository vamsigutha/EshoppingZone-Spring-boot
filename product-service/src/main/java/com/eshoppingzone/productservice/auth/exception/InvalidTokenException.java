package com.eshoppingzone.productservice.auth.exception;



public class InvalidTokenException extends RuntimeException{
    public InvalidTokenException() {
        super("Invalid Token");
    }
}
