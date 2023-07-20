package com.example.tp_spring_security.controller;


import com.example.tp_spring_security.dto.UserDto;
import com.example.tp_spring_security.entity.User;
import com.example.tp_spring_security.entity.UserDetailsImpl;
import com.example.tp_spring_security.repository.UserRepository;
import com.example.tp_spring_security.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginController {


    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        User user1 = null;
        ResponseEntity response = null;

        try {
            String hashPwd = passwordEncoder.encode(user.getPassword());
            user.setPassword(hashPwd);

            user1 = userRepository.save(user);

            if (user1.getId() > 0) {
                response = ResponseEntity.status(HttpStatus.CREATED).body("User successfully registered");
            }

        } catch (Exception ex) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An exeception occured : " + ex.getMessage());
        }
        return response;
    }


    @PostMapping("/login")
    public ResponseEntity<UserDto> loginUser(@RequestBody UserDto userDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            String username = userDetails.getUsername();

            String token = jwtUtils.generateJwtToken(authentication);

            //String role = userDetails.getAuthorities().toArray()[0].toString().equals("ROLE_ADMIN") ? "Administrator" : "Customer";

            return ResponseEntity.ok(UserDto.builder().token(token).id((long) userDetails.getId()).username(username).build());

        } catch (Exception e) {
            throw e;
        }

    }


}
