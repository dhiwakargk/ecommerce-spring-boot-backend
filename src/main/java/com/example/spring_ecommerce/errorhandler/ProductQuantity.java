package com.example.spring_ecommerce.errorhandler;

public class ProductQuantity extends RuntimeException 
{
    public ProductQuantity(String message)
    {
        super(message);
    }    
}
