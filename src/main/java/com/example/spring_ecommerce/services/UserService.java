package com.example.spring_ecommerce.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.spring_ecommerce.dtos.LoginDTO;
import com.example.spring_ecommerce.dtos.UserDTO;
import com.example.spring_ecommerce.errorhandler.UserAlreadyExists;
import com.example.spring_ecommerce.errorhandler.UserIdNotFound;
import com.example.spring_ecommerce.models.CartModal;
import com.example.spring_ecommerce.models.OrderModal;
import com.example.spring_ecommerce.models.PaymentModal;
import com.example.spring_ecommerce.models.UserModal;
import com.example.spring_ecommerce.repository.UserRepo;

@Service
public class UserService 
{
   @Autowired
   private UserRepo user_repo;

   @Autowired
   private ModelMapper model_mapper;

   @Autowired
   private AuthenticationManager auth_manager;

   @Autowired
   private JwtTokenService jwt_service;
   
   public List<UserModal> Get_All_Users()
   {
      return user_repo.findAll();
   }

   public ResponseEntity<Object> User_Login_DB(LoginDTO login)
   {
      UserModal user=user_repo.findByUserEmail(login.getLoginEmail());
      if(user==null)
      {
          throw new UserIdNotFound("The email is:"+login.getLoginEmail()+"not found for login");
      }
      else 
      {
         try 
         {
         UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(login.getLoginEmail(),login.getLoginPassword());
         Authentication authentication=auth_manager.authenticate(token);
         if(authentication.isAuthenticated())
         {
            String jwt_token=jwt_service.Generate_JWT_Token(login.getLoginEmail());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("jwt token is: "+jwt_token);
         }
         }
         catch(Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User Name and Password wrong");
         }
         return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User Name and Password wrong");
         
      }
   }

   public ResponseEntity<Object> Get_User_DB(int user_id)
   {
      UserModal user=user_repo.findById(user_id).orElse(null);
      if(user==null)
      {
         throw new UserIdNotFound("The User id: "+user_id+" is not found");
      }
      else 
      {
         UserDTO user_dto=model_mapper.map(user, UserDTO.class);
         return ResponseEntity.status(HttpStatus.ACCEPTED).body(user_dto);
      }
   }

   public ResponseEntity<Object> Create_User_DB(UserModal user)
   {
      UserModal user_found=user_repo.findByUserEmail(user.getUserEmail());
      if(user_found==null)
      {  
          user.setUserPassword(new BCryptPasswordEncoder().encode(user.getUserPassword()));
          UserModal create_user=user_repo.save(user);
          UserDTO user_dto=model_mapper.map(create_user, UserDTO.class);
         return ResponseEntity.status(HttpStatus.CREATED).body(user_dto);
      }
      else 
      {
         throw new UserAlreadyExists("The user email: "+user.getUserEmail()+" is already Exists");
      }
   }

   public ResponseEntity<Object> Update_User_DB(UserModal user)
   {
      UserModal user_found=user_repo.findById(user.getUserId()).orElse(null);
      if(user_found!=null)
      {
         user.setUserPassword(new BCryptPasswordEncoder().encode(user.getUserPassword()));
         UserModal update_user=user_repo.save(user);
         UserDTO user_dto=model_mapper.map(update_user, UserDTO.class);
         return ResponseEntity.status(HttpStatus.ACCEPTED).body(user_dto);
      }
      else 
      {
          throw new UserIdNotFound("The user id: "+user.getUserId()+" is not found for update");
      }
      
   }

   public ResponseEntity<Object> Delete_User_DB(int id)
   {
      UserModal user_found=user_repo.findById(id).orElse(null);
      if(user_found!=null)
      {
         user_repo.deleteById(id);
         return ResponseEntity.status(HttpStatus.NO_CONTENT).body("The User id is deleted successfully");
      }
      else 
      {
          throw new UserIdNotFound("The user id: "+id+" is not found for delete");
      }
   }
   
   public ResponseEntity<Object> Get_User_Cart_DB(int user_id)
   {
      UserModal user=user_repo.findById(user_id).orElse(null);
      if(user==null)
      {
         throw new UserIdNotFound("The user id: "+user_id+" is not found");
      }
      else 
      {
         List<CartModal> carts=new ArrayList<>(user.getCarts());
         return ResponseEntity.status(HttpStatus.OK).body(carts);
      }
   }

    public ResponseEntity<Object> Get_User_Order_DB(int user_id)
   {
      UserModal user=user_repo.findById(user_id).orElse(null);
      if(user==null)
      {
         throw new UserIdNotFound("The user id: "+user_id+" is not found");
      }
      else 
      {
         List<OrderModal> orders=new ArrayList<>(user.getOrders());
         return ResponseEntity.status(HttpStatus.OK).body(orders);
      }
   }

   public ResponseEntity<Object> Get_User_Payment_DB(int user_id)
   {
      UserModal user=user_repo.findById(user_id).orElse(null);
      if(user==null)
      {
         throw new UserIdNotFound("The user id: "+user_id+" is not found");
      }
      else 
      {
         List<PaymentModal> payments=new ArrayList<>(user.getPayments());
         return ResponseEntity.status(HttpStatus.OK).body(payments);
      }
   }


   
   
}
