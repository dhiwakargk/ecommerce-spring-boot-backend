package com.example.spring_ecommerce.errorhandler;

public class UserAlreadyExists extends RuntimeException 
{
    public UserAlreadyExists(String message)
    {
        super(message);
    }    
}
