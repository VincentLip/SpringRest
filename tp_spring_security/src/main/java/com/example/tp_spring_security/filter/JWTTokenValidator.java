package com.example.tp_spring_security.filter;


import com.example.tp_spring_security.constants.SecurityConstants;
import com.example.tp_spring_security.service.impl.UserDetailsServiceImpl;
import com.example.tp_spring_security.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JWTTokenValidator extends OncePerRequestFilter {


    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String jwt = request.getHeader(SecurityConstants.JWT_HEADER);

        if(jwt != null && jwtUtils.validateJwtToken(jwt)){

            try{

                String userName = jwtUtils.getUserNameFromJwt(jwt);
                UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }catch (Exception e){
                throw  new BadCredentialsException("Le token est invalide");
            }
        }



        filterChain.doFilter(request,response);


    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request){
        return request.getServletPath().equals("/login");
    }


}
