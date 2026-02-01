package com.example.spring_ecommerce.globalerrorhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.spring_ecommerce.errorhandler.CartIdNotFound;
import com.example.spring_ecommerce.errorhandler.OrderIdNotFound;
import com.example.spring_ecommerce.errorhandler.ProductIdNotFound;
import com.example.spring_ecommerce.errorhandler.ProductQuantity;
import com.example.spring_ecommerce.errorhandler.UserAlreadyExists;
import com.example.spring_ecommerce.errorhandler.UserIdNotFound;

@RestControllerAdvice
public class GlobalErrorHandler 
{
    @ExceptionHandler(UserAlreadyExists.class)
    public ResponseEntity<Object> handle_Admin_Alredy_Exists(UserAlreadyExists ex)
    {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    } 
    
    @ExceptionHandler(UserIdNotFound.class)
    public ResponseEntity<Object> handle_Admin_Id_Not_Found(UserIdNotFound ex)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ProductIdNotFound.class)
    public ResponseEntity<Object> handle_Product_Id_Not_Found(ProductIdNotFound ex)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ProductQuantity.class)
    public ResponseEntity<Object> handle_Product_Quantity(ProductQuantity ex)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(OrderIdNotFound.class)
    public ResponseEntity<Object> handle_Order_Id_Not_Found(OrderIdNotFound ex)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(CartIdNotFound.class)
    public ResponseEntity<Object> handle_Cart_Id_Not_Found(CartIdNotFound ex)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
