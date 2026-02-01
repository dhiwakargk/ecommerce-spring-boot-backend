package com.example.spring_ecommerce.errorhandler;

public class UserIdNotFound extends RuntimeException {
    
    public UserIdNotFound(String message)
    {
        super(message);
    }
}
