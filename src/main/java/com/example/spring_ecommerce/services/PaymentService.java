package com.example.spring_ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring_ecommerce.models.PaymentModal;
import com.example.spring_ecommerce.models.UserModal;
import com.example.spring_ecommerce.repository.PaymentRepo;
import com.example.spring_ecommerce.repository.UserRepo;

@Service
public class PaymentService 
{
   @Autowired
   private PaymentRepo payment_repo; 
   
   @Autowired
   private UserRepo user_repo;

   public String Save_Payment(PaymentModal payment)
   {
         UserModal user=user_repo.findById(payment.getPaymentUserId()).orElse(null);
         user.getPayments().add(payment);
         payment.setUser(user);    
         payment_repo.save(payment);
         return "Payment Success";
   }
}
