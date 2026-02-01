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

import com.example.spring_ecommerce.dtos.AdminDTO;
import com.example.spring_ecommerce.dtos.LoginDTO;
import com.example.spring_ecommerce.errorhandler.UserAlreadyExists;
import com.example.spring_ecommerce.errorhandler.UserIdNotFound;
import com.example.spring_ecommerce.models.AdminModal;
import com.example.spring_ecommerce.models.ProductModal;
import com.example.spring_ecommerce.repository.AdminRepo;

@Service
public class AdminService 
{
    @Autowired
    private AdminRepo admin_repo;

    @Autowired
    private ModelMapper model_mapper;

    @Autowired
    private AuthenticationManager auth_manager;

    @Autowired
    private JwtTokenService jwt_service;

    public List<AdminModal> All_Admin_DB()
    {
        return admin_repo.findAll();
    }

    public ResponseEntity<Object> Get_Admin_DB(int admin_id)
    {
        AdminModal get_admin=admin_repo.findById(admin_id).orElse(null);
        if(get_admin==null)
        {
            throw new UserIdNotFound("The admin id: "+admin_id+" is not found");
        }
        else 
        {
            AdminDTO admin_dto=model_mapper.map(get_admin,AdminDTO.class);
            return  ResponseEntity.status(HttpStatus.OK).body(admin_dto);
        }
    }

    public ResponseEntity<Object> Login_Admin_DB(LoginDTO login)
    {
        AdminModal admin=admin_repo.findByAdminEmail(login.getLoginEmail());
        if(admin==null)
        {
            throw new UserIdNotFound("The admin email: "+login.getLoginEmail()+" is not found for login");
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
                return ResponseEntity.status(HttpStatus.ACCEPTED).body("Jwt token is: "+jwt_token);
            }
            }
            catch(Exception e){
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Admin Email and Password Wrong");
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Admin Email and Password Wrong");
        }
    }

    public ResponseEntity<Object> Get_Admin_Product_DB(int admin_id)
    {
        AdminModal admin=admin_repo.findById(admin_id).orElse(null);
        if(admin==null)
        {
            throw new UserIdNotFound("The admin id: "+admin_id+" is not found");
        }
        else 
        {
            List<ProductModal> products=new ArrayList<>(admin.getProducts());
            return ResponseEntity.status(HttpStatus.OK).body(products);
        }
    }

    public ResponseEntity<Object> Create_Admin_DB(AdminModal admin)
    {
      AdminModal exists_admin=admin_repo.findByAdminEmail(admin.getAdminEmail());
      if(exists_admin==null)
      {
         admin.setAdminPassword(new BCryptPasswordEncoder().encode(admin.getAdminPassword()));
         AdminModal create_admin=admin_repo.save(admin);
         AdminDTO admin_dto=model_mapper.map(create_admin, AdminDTO.class);
         return ResponseEntity.status(HttpStatus.CREATED).body(admin_dto);
      }
      else 
      {
        throw new UserAlreadyExists("The Admin Email is: "+admin.getAdminEmail()+" is already exists");
      }
      
    }

    public ResponseEntity<Object> Update_Admin_DB(AdminModal admin)
    {
        AdminModal found=admin_repo.findById(admin.getAdminId()).orElse(null);
        if(found!=null)
        {
            admin.setAdminPassword(new BCryptPasswordEncoder().encode(admin.getAdminPassword()));
            AdminModal update_admin=admin_repo.save(admin);
            AdminDTO admin_dto=model_mapper.map(update_admin, AdminDTO.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(admin_dto);
        }
        else 
        {
            throw new UserIdNotFound("The admin id: "+admin.getAdminId()+" is not found!");
        }
    }

    public ResponseEntity<Object> Delete_Admin_DB(int admin_id)
    {
        
        AdminModal found=admin_repo.findById(admin_id).orElse(null);
        if(found!=null)
        {
            admin_repo.deleteById(admin_id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Admin Deleted Successfully");
        }
        else 
        {
            throw new UserIdNotFound("The admin id: "+admin_id+" is not found!");
        }
    }

}
