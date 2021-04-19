package com.eshoppingzone.paymentservice.auth.exception;



public class InvalidTokenException extends RuntimeException{
    public InvalidTokenException() {
        super("Invalid Token");
    }
}
