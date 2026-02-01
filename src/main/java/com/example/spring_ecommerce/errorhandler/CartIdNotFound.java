package com.example.spring_ecommerce.errorhandler;

public class CartIdNotFound extends RuntimeException 
{
   public CartIdNotFound(String message)
   {
       super(message);
   }    
}
