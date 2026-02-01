package com.example.spring_ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_ecommerce.models.PaymentModal;


public interface PaymentRepo extends JpaRepository<PaymentModal, Integer> {
 
    PaymentModal findByPaymentOrderId(int paymentOrderId);
}
