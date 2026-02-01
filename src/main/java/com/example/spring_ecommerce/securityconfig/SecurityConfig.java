package com.example.spring_ecommerce.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.spring_ecommerce.services.LoginService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled=true,securedEnabled = true)
public class SecurityConfig
{
    @Autowired
    private LoginService login_service;

    @Autowired
    private JwtFilter jwt_filter;    

    @Bean
    public SecurityFilterChain SecurityChain(HttpSecurity https)
    {
       https.authorizeHttpRequests(http->
        http.requestMatchers("/user/create","/user/login","/admin/create","/admin/login").permitAll()
        .requestMatchers("/user/**","/order/**","/cart/**","/payment/**").hasRole("USER")
        .requestMatchers("/admin/**","/product/**").hasRole("ADMIN")
        .anyRequest().authenticated()
       ).csrf(csrf->csrf.disable()).formLogin(form->form.disable())
       .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
       .addFilterBefore(jwt_filter,UsernamePasswordAuthenticationFilter.class);
       
       return https.build();
    }
    
    @Bean
    public AuthenticationProvider auth_provider()
    {
        DaoAuthenticationProvider dao_auth=new DaoAuthenticationProvider(login_service);
        dao_auth.setPasswordEncoder(new BCryptPasswordEncoder());
        return dao_auth;
    }

    @Bean
    public AuthenticationManager auth_manager(AuthenticationConfiguration config)
    {
        return config.getAuthenticationManager();
    }
}