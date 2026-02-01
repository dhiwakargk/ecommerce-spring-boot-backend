package com.example.spring_ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_ecommerce.models.OrderModal;

public interface OrderRepo extends JpaRepository<OrderModal, Integer> {
    
}
