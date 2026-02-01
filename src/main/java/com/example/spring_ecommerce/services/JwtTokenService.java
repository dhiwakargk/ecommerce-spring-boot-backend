package com.example.spring_ecommerce.services;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenService 
{
   private String secret="tahfvbnwopql6284bzxhato37fgmnaeyp";
   
   private SecretKey keys=Keys.hmacShaKeyFor(secret.getBytes());

   public String Generate_JWT_Token(String email)
   {
      return Jwts.builder()
      .subject(email)
      .issuedAt(new Date())
      .expiration(new Date(System.currentTimeMillis()+1000*60*60))
      .signWith(keys)
      .compact();
   }

   public Claims Claim_JWT_Token(String token)
   {
      return Jwts.parser().verifyWith(keys)
      .build()
      .parseSignedClaims(token).getPayload();
   }

   public String Get_Email_From_JWT(String token)
   {
      return Claim_JWT_Token(token).getSubject();
   }

   public boolean Validate_JWT_Token(String token,UserDetails userdetails)
   {
      String email=Get_Email_From_JWT(token);
      if(email.equals(userdetails.getUsername()))
      {
         return true;
      }
      else 
      {
         return false;
      }
   }

}
