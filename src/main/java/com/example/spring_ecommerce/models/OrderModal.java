package com.example.spring_ecommerce.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class OrderModal 
{
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private int orderId;
   

   @Column(nullable=false)
   private int orderProductId;

   @Column(nullable=false)
   private String orderProductImage;

   @Column(nullable=false)
   private String orderName;

   @Column(nullable=false)
   private double orderPrice;

   @Column(nullable=false)
   private int orderQuantity;

   private Date orderDate=new Date();
  
   private int orderTotalPrice;

   @JsonBackReference("user-orders")
   @ManyToOne
   @JoinColumn(name="userId")
   private UserModal user;

   public OrderModal(){}

   public int getOrderId() {
    return orderId;
   }

   public void setOrderId(int orderId) {
    this.orderId = orderId;
   }

   public int getOrderProductId() {
    return orderProductId;
   }

   public void setOrderProductId(int orderProductId) {
    this.orderProductId = orderProductId;
   }

   public String getOrderProductImage() {
    return orderProductImage;
   }

   public void setOrderProductImage(String orderProductImage) {
    this.orderProductImage = orderProductImage;
   }

   public String getOrderName() {
    return orderName;
   }

   public void setOrderName(String orderName) {
    this.orderName = orderName;
   }

   public double getOrderPrice() {
    return orderPrice;
   }

   public void setOrderPrice(double orderPrice) {
    this.orderPrice = orderPrice;
   }

   public int getOrderQuantity() {
    return orderQuantity;
   }

   public void setOrderQuantity(int orderQuantity) {
    this.orderQuantity = orderQuantity;
   }

   public Date getOrderDate() {
    return orderDate;
   }

   public void setOrderDate(Date orderDate) {
    this.orderDate = orderDate;
   }

   public int getOrderTotalPrice() {
    return orderTotalPrice;
   }

   public void setOrderTotalPrice(int orderTotalPrice) {
    this.orderTotalPrice = orderTotalPrice;
   }

   public UserModal getUser() {
    return user;
   }

   public void setUser(UserModal user) {
    this.user = user;
   }

   
  

   
   
}
