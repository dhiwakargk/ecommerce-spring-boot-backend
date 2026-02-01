package com.example.spring_ecommerce.securityconfig;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.spring_ecommerce.services.JwtTokenService;
import com.example.spring_ecommerce.services.LoginService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter
{
    @Autowired
    private LoginService login_service;

    @Autowired
    private JwtTokenService jwt_token;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException 
    {
        String authHeader=request.getHeader("Authorization");
        

        String token=null;
        String userEmail=null;

        if(authHeader != null && authHeader.startsWith("Bearer ")) 
        {
           token=authHeader.substring(7);
           userEmail=jwt_token.Get_Email_From_JWT(token);
        }

        if(userEmail!=null && SecurityContextHolder.getContext().getAuthentication()==null)
        {
            UserDetails user=login_service.loadUserByUsername(userEmail);
            if(jwt_token.Validate_JWT_Token(token, user))
            {
                UsernamePasswordAuthenticationToken tokens=new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(tokens);
            }
        }

        filterChain.doFilter(request, response);
    }
    
}
