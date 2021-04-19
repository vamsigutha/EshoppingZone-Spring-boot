package com.eshoppingzone.cartservice.auth.exception;

import static java.lang.String.format;

public class FailedToLoginException extends RuntimeException{
    public FailedToLoginException(String username) {
        super(format("Failed to login with username %s", username));
    }
}
