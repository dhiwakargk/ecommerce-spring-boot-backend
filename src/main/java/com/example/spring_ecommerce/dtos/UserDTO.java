package com.example.spring_ecommerce.dtos;

public class UserDTO 
{
   
   private int userId;

   private String userName;

   private String userEmail;

   private String userAddress;

   private boolean user;

   public UserDTO(){}

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

   public String getUserEmail() {
      return userEmail;
   }

   public void setUserEmail(String userEmail) {
      this.userEmail = userEmail;
   }

   public String getUserAddress() {
      return userAddress;
   }

   public void setUserAddress(String userAddress) {
      this.userAddress = userAddress;
   }

   public boolean isUser() {
      return user;
   }

   public void setUser(boolean user) {
      this.user = user;
   }

   

   

   

   
}
