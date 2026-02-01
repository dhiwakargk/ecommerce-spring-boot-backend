package com.example.spring_ecommerce.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.spring_ecommerce.errorhandler.UserIdNotFound;
import com.example.spring_ecommerce.models.AdminModal;
import com.example.spring_ecommerce.models.UserModal;
import com.example.spring_ecommerce.repository.AdminRepo;
import com.example.spring_ecommerce.repository.UserRepo;

@Service
public class LoginService implements UserDetailsService 
{
    @Autowired
    private UserRepo user_repo;

    @Autowired
    private AdminRepo admin_repo;

    @Override
    public UserDetails loadUserByUsername(String email) 
    {
        UserModal user=user_repo.findByUserEmail(email);
        AdminModal admin=admin_repo.findByAdminEmail(email);
        
        if(user==null && admin==null)
        {
            throw new UserIdNotFound("The user email:"+email+" is not found for login");
        }
        List<GrantedAuthority> authorities=new ArrayList<>();
        if(user!=null)
        {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            return new User(user.getUserEmail(),user.getUserPassword(),authorities);
        }
        else 
        {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            return new User(admin.getAdminEmail(),admin.getAdminPassword(),authorities);
        }

        

        
    }
    
}
