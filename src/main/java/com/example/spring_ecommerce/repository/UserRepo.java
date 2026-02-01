package com.example.spring_ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring_ecommerce.models.UserModal;

@Repository
public interface  UserRepo extends JpaRepository<UserModal, Integer> {
  
    UserModal findByUserEmail(String email);
}
