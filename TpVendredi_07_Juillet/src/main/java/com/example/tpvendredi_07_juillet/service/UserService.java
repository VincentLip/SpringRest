package com.example.tpvendredi_07_juillet.service;

import com.example.tpvendredi_07_juillet.dto.UserDto;
import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    List<UserDto> getAllUsers();

    UserDto getUserById( Integer id);

    UserDto updateUser(UserDto userDto, Integer id);

    void deleteUserById(Integer id);
}
