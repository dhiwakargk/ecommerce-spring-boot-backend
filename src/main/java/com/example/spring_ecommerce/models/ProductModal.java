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
public class ProductModal 
{
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int productId;
  
  @Column(nullable=false)
  private String productName;

  @Column(nullable=false)
  private String productImageUrl;

  @Column(nullable=false)
  private int productPrice;

  @Column(nullable=false)
  private int productStock;

  @JsonBackReference("admin-products")
  @ManyToOne
  @JoinColumn(name="adminId")
  private AdminModal admin;

  public ProductModal(){}

  public int getProductId() {
    return productId;
  }

  public void setProductId(int productId) {
    this.productId = productId;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public int getProductPrice() {
    return productPrice;
  }

  public void setProductPrice(int productPrice) {
    this.productPrice = productPrice;
  }

  public int getProductStock() {
    return productStock;
  }

  public void setProductStock(int productStock) {
    this.productStock = productStock;
  }

  public AdminModal getAdmin() {
    return admin;
  }

  public void setAdmin(AdminModal admin) {
    this.admin = admin;
  }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

   

}
