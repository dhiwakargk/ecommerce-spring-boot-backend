package com.example.spring_ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_ecommerce.models.CartModal;
import com.example.spring_ecommerce.services.CartService;


@RestController
@RequestMapping("/cart")
public class CartController 
{
    @Autowired
    private CartService cart_service;

   

    @GetMapping("")
    public List<CartModal> All_Carts() 
    {
        return cart_service.All_Carts_DB();
    }
    

    @PostMapping("/create")
    public ResponseEntity<Object> Create_Cart(@RequestBody CartModal cart,@RequestParam int user_id , @RequestParam int product_id)
    {
       return cart_service.Create_Cart_DB(cart, user_id, product_id);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<Object> Order_Cart(@PathVariable(name="id") int cart_id) 
    {
        return cart_service.Cart_Order_DB(cart_id);
    }

    @GetMapping("/order/all/{id}")
    public ResponseEntity<Object> Order_Total_Cart(@PathVariable(name="id") int user_id) 
    {
        return cart_service.Cart_Total_Order_DB(user_id);
    }
    
    
}
