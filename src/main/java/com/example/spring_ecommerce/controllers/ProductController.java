package com.example.spring_ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_ecommerce.models.ProductModal;
import com.example.spring_ecommerce.services.ProductService;



@RestController
@RequestMapping("/product")
public class ProductController 
{
    @Autowired
    private ProductService product_service;

    @GetMapping("")
    public List<ProductModal> All_Product() 
    {
        return product_service.All_Product_DB();
    }
    

    @PostMapping("/create/{id}")
    public ResponseEntity<Object> Create_Product(@RequestBody ProductModal product,@PathVariable(name="id") int admin_id)
    {        
        return product_service.Create_Product_DB(product, admin_id);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> Update_Product(@RequestBody ProductModal product) 
    {
            
        return product_service.Update_Product_DB(product);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> Delete_Product(@PathVariable(name="id") int product_id)
    {
        return product_service.Delete_product_DB(product_id);
    }
    
    


}
