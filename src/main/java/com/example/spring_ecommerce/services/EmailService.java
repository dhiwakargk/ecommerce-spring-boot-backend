package com.example.spring_ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.spring_ecommerce.models.OrderModal;
import com.example.spring_ecommerce.models.UserModal;
import com.example.spring_ecommerce.repository.UserRepo;

@Service
public class EmailService  
{
   @Autowired
   private UserRepo user_repo;

   @Autowired
   private JavaMailSender java_mail_sender;

   @Value("${spring.mail.username}")
   private String sender_email;
   
   public void Send_Email(OrderModal order,int user_id)
   {
      
      try 
      {
      SimpleMailMessage mail=new SimpleMailMessage();
      UserModal user=user_repo.findById(user_id).orElse(null);
      mail.setFrom(sender_email);
      mail.setTo(user.getUserEmail());
      mail.setSubject("Order Purchasing Detail");
      mail.setText("Your Order "+order.getOrderName()+"\nquantity "+order.getOrderQuantity()+"\ntotal price "+order.getOrderTotalPrice()+" is successfully placed...");
      java_mail_sender.send(mail);
      System.out.println("Mail sended Successfully............");
      }
      catch(Exception e){
         System.out.println("Mail not sended...............");
      }
      
   }    
}
