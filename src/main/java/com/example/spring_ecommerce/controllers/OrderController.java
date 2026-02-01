package com.example.spring_ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_ecommerce.models.OrderModal;
import com.example.spring_ecommerce.services.OrderService;


@RestController
@RequestMapping("/order")
public class OrderController 
{
    @Autowired
    private OrderService order_service;

    @PostMapping("/create")
    public ResponseEntity<Object> Create_Order(@RequestBody OrderModal order,@RequestParam int user_id,@RequestParam int product_id)
    {
      return order_service.Create_Order_DB(order, user_id, product_id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> Delete_Order(@PathVariable(name="id") int order_id) 
    {
        return order_service.Delete_Order_DB(order_id);
    }
    
}
