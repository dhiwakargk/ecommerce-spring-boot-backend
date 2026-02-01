package com.example.spring_ecommerce.dtos;



public class LoginDTO 
{
    
  
   private String loginEmail;

  
   private String loginPassword;

  public LoginDTO(){}

  public String getLoginEmail() {
    return loginEmail;
  }

  public void setLoginEmail(String loginEmail) {
    this.loginEmail = loginEmail;
  }

  public String getLoginPassword() {
    return loginPassword;
  }

  public void setLoginPassword(String loginPassword) {
    this.loginPassword = loginPassword;
  }

  
    
}
