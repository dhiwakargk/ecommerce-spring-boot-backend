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
import com.example.spring_ecommerce.models.AdminModal;
import com.example.spring_ecommerce.services.AdminService;



@RestController
@RequestMapping("/admin")
public class AdminController 
{
    @Autowired
    private AdminService admin_service;

    @GetMapping("")
    public List<AdminModal> All_Admin() 
    {
        return admin_service.All_Admin_DB();
    }

    @PostMapping("/login")
    public ResponseEntity<Object> Admin_Login(@RequestBody LoginDTO login) 
    {
        return admin_service.Login_Admin_DB(login);
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> Get_Admin(@PathVariable(name="id") int admin_id) 
    {
        return admin_service.Get_Admin_DB(admin_id);
    }
    
    @GetMapping("/product/{id}")
    public ResponseEntity<Object> Get_Admin_Product(@PathVariable(name="id") int admin_id) 
    {
        return admin_service.Get_Admin_Product_DB(admin_id);
    }
    

   @PostMapping("/create")
   public ResponseEntity<Object> Create_Admin(@RequestBody AdminModal admin ) 
   {
       
       return admin_service.Create_Admin_DB(admin);
   }

   @PutMapping("/update")
   public ResponseEntity<Object> Update_Admin(@RequestBody AdminModal admin) 
   {
       return admin_service.Update_Admin_DB(admin);
   }

   @DeleteMapping("/delete/{id}")
   public ResponseEntity<Object> Delete_Admin(@PathVariable(name="id") int admin_id)
   {
      return admin_service.Delete_Admin_DB(admin_id);
   }
   
   
        
}
