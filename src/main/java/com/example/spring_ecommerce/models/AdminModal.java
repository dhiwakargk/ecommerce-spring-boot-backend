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
public class AdminModal 
{
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private int adminId;
   
   @Column(nullable=false)
   private String adminName;

   @Column(nullable=false)
   private String adminEmail;

   @Column(nullable=false)
   private String adminPassword;

   private boolean admin=true;

   @JsonManagedReference("admin-products")
   @OneToMany(mappedBy="admin",cascade=CascadeType.ALL)
   private List<ProductModal> products;

   public AdminModal(){}

   public int getAdminId() {
    return adminId;
   }

   public void setAdminId(int adminId) {
    this.adminId = adminId;
   }

   public String getAdminName() {
    return adminName;
   }

   public void setAdminName(String adminName) {
    this.adminName = adminName;
   }

   public String getAdminEmail() {
    return adminEmail;
   }

   public void setAdminEmail(String adminEmail) {
    this.adminEmail = adminEmail;
   }

   public String getAdminPassword() {
    return adminPassword;
   }

   public void setAdminPassword(String adminPassword) {
    this.adminPassword = adminPassword;
   }

   public boolean isAdmin() {
    return admin;
   }

   public void setAdmin(boolean admin) {
    this.admin = admin;
   }

   public List<ProductModal> getProducts() {
    return products;
   }

   public void setProducts(List<ProductModal> products) {
    this.products = products;
   }
    
   

}
