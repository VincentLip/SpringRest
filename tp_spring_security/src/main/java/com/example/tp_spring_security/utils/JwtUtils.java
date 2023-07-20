package com.example.tp_spring_security.utils;


import com.example.tp_spring_security.constants.SecurityConstants;
import com.example.tp_spring_security.entity.UserDetailsImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtUtils {


    public String generateJwtToken(Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime()+3600000))
                .signWith(Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8))).compact();

    }

    public boolean validateJwtToken(String token){
        try{
            Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8))).build().parse(token);
            return true;
        }catch (Exception ex){
            return false;
        }

    }

    public String getUserNameFromJwt(String token){
        return String.valueOf(Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes()))
                .build().parseClaimsJws(token).getBody().getSubject());
    }



}
