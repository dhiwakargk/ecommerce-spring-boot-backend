package com.example.spring_ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_ecommerce.models.ProductModal;

public interface  ProductRepo extends JpaRepository<ProductModal, Integer> {
    
}
