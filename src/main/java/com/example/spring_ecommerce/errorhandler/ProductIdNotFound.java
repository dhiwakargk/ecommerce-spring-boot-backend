package com.example.spring_ecommerce.errorhandler;

public class ProductIdNotFound extends RuntimeException
{
    public ProductIdNotFound(String message)
    {
        super(message);
    }
}
