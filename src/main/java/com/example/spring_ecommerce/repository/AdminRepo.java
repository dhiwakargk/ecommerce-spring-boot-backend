package com.example.spring_ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_ecommerce.models.AdminModal;

public interface  AdminRepo extends JpaRepository<AdminModal, Integer> {
    
    AdminModal findByAdminEmail(String email);
}
