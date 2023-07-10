package com.example.tpvendredi_07_juillet.controller;

import com.example.tpvendredi_07_juillet.dto.UserDto;
import com.example.tpvendredi_07_juillet.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;
    }

    @PostMapping("/api/v1/users")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }

    @GetMapping("/api/v1/users")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return new ResponseEntity(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping(value = "/api/v1/users/{id}")
    public ResponseEntity<UserDto> getUserByIdV1(@PathVariable(name = "id") Integer id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/api/v1/users/{id}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable(name = "id") Integer id){

        UserDto userResponse = userService.updateUser(userDto, id);

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @DeleteMapping("/api/v1/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(name = "id") Integer id){

        userService.deleteUserById(id);

        return new ResponseEntity<>("User entity deleted successfully.", HttpStatus.OK);
    }
}
