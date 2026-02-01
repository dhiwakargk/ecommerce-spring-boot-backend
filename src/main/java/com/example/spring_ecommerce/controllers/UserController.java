package com.example.spring_ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_ecommerce.dtos.LoginDTO;
import com.example.spring_ecommerce.models.UserModal;
import com.example.spring_ecommerce.services.UserService;





@RestController
@RequestMapping("/user")
public class UserController 
{
    @Autowired
    private UserService user_service;

    @GetMapping("")
    public List<UserModal> Get_All()
    {
        return user_service.Get_All_Users();
    }

    @PostMapping("/login")
    public ResponseEntity<Object> User_Login(@RequestBody LoginDTO login) 
    {
        return user_service.User_Login_DB(login);
    }
    
    
    @GetMapping("/cart/{id}")
    public ResponseEntity<Object> Get_User_Cart(@PathVariable(name="id") int user_id) 
    {
        return user_service.Get_User_Cart_DB(user_id);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<Object> Get_User_Order(@PathVariable(name="id") int user_id) 
    {
        return user_service.Get_User_Order_DB(user_id);
    }
    
    @GetMapping("/payment/{id}")
    public ResponseEntity<Object> Get_User_Payment(@PathVariable(name="id") int user_id) 
    {
        return user_service.Get_User_Payment_DB(user_id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> Get_User(@PathVariable(name="id") int user_id) 
    {
        return user_service.Get_User_DB(user_id);
    }
    

    @PostMapping("/create")
    public ResponseEntity<Object> Create_User(@RequestBody UserModal user)
    {
        return user_service.Create_User_DB(user);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> Update_User(@RequestBody UserModal user) 
    {
        
        return user_service.Update_User_DB(user);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> Delete_User(@PathVariable(name="id") int user_id )
    {
       return user_service.Delete_User_DB(user_id);
    }
}
