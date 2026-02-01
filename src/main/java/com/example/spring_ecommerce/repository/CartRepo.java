package com.example.spring_ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_ecommerce.models.CartModal;

public interface CartRepo extends JpaRepository<CartModal, Integer> {
    
}
