package com.example.spring_ecommerce.errorhandler;

public class OrderIdNotFound extends RuntimeException 
{
   public OrderIdNotFound(String message)
   {
       super(message);
   }    
}
