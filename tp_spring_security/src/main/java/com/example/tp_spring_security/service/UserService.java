package com.example.tp_spring_security.service;

import com.example.tp_spring_security.dto.UserDto;
import com.example.tp_spring_security.entity.User;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto getUserById(Long id);

    void deleteUser( Long id);
}
