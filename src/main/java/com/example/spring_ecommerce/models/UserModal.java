package com.example.spring_ecommerce.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class UserModal 
{
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private int userId;
   
   @Column(nullable=false)
   private String userName;

   @Column(nullable=false)
   private String userPassword;

   @Column(nullable=false)
   private String userEmail;

   @Column(nullable=false)
   private String userAddress;

   private boolean user=true;

   @JsonManagedReference("user-orders")
   @OneToMany(mappedBy="user",cascade=CascadeType.ALL)
   private List<OrderModal> orders;

   @JsonManagedReference("user-carts")
   @OneToMany(mappedBy="user",cascade=CascadeType.ALL,orphanRemoval=true)
   private List<CartModal> carts;

   @JsonManagedReference("user-payment")
   @OneToMany(mappedBy="user",cascade=CascadeType.ALL)
   private List<PaymentModal> payments;

   public UserModal(){}

   public int getUserId() {
    return userId;
   }

   public void setUserId(int userId) {
    this.userId = userId;
   }

   public String getUserName() {
    return userName;
   }

   public void setUserName(String userName) {
    this.userName = userName;
   }

   public String getUserPassword() {
    return userPassword;
   }

   public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
   }

   public String getUserEmail() {
    return userEmail;
   }

   public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
   }

   

   public boolean isUser() {
    return user;
   }

   public void setUser(boolean user) {
    this.user = user;
   }

   public List<OrderModal> getOrders() {
    return orders;
   }

   public void setOrders(List<OrderModal> orders) {
    this.orders = orders;
   }

   public List<CartModal> getCarts() {
    return carts;
   }

   public void setCarts(List<CartModal> carts) {
    this.carts = carts;
   }

   public List<PaymentModal> getPayments() {
    return payments;
   }

   public void setPayments(List<PaymentModal> payments) {
    this.payments = payments;
   }

   public String getUserAddress() {
    return userAddress;
   }

   public void setUserAddress(String userAddress) {
    this.userAddress = userAddress;
   }

   

}
