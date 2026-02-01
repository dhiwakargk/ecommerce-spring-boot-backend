package com.example.spring_ecommerce.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CartModal 
{
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
   private int cartId;
   
   @Column(nullable=false)
   private int cartProductId;

   @Column(nullable=false)
   private String cartProductName;

   @Column(nullable=false)
   private String cartProductImage;

   @Column(nullable=false)
   private double cartProductPrice;

   @Column(nullable=false)
   private int cartProductQuantity;

   private int cartTotalPrice;    

   @JsonBackReference("user-carts")
   @ManyToOne
   @JoinColumn(name="userId")
   private UserModal user;

   public CartModal(){}

   public int getCartId() {
     return cartId;
   }

   public void setCartId(int cartId) {
     this.cartId = cartId;
   }

   public int getCartProductId() {
     return cartProductId;
   }

   public void setCartProductId(int cartProductId) {
     this.cartProductId = cartProductId;
   }

   public String getCartProductName() {
     return cartProductName;
   }

   public void setCartProductName(String cartProductName) {
     this.cartProductName = cartProductName;
   }

   public double getCartProductPrice() {
     return cartProductPrice;
   }

   public void setCartProductPrice(double cartProductPrice) {
     this.cartProductPrice = cartProductPrice;
   }

   public int getCartProductQuantity() {
     return cartProductQuantity;
   }

   public void setCartProductQuantity(int cartProductQuantity) {
     this.cartProductQuantity = cartProductQuantity;
   }

   public int getCartTotalPrice() {
     return cartTotalPrice;
   }

   public void setCartTotalPrice(int cartTotalPrice) {
     this.cartTotalPrice = cartTotalPrice;
   }

   public UserModal getUser() {
     return user;
   }

   public void setUser(UserModal user) {
     this.user = user;
   }

    public String getCartProductImage() {
        return cartProductImage;
    }

    public void setCartProductImage(String cartProductImage) {
        this.cartProductImage = cartProductImage;
    }

   
   

   
}
