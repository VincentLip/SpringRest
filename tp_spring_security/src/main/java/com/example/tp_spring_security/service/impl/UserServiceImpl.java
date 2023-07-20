package com.example.tp_spring_security.service.impl;

import com.example.tp_spring_security.dto.UserDto;
import com.example.tp_spring_security.entity.Todo;
import com.example.tp_spring_security.entity.User;
import com.example.tp_spring_security.exception.ResourceNotFoundException;
import com.example.tp_spring_security.repository.UserRepository;
import com.example.tp_spring_security.service.UserService;
import com.example.tp_spring_security.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Mapper mapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = mapper.mapToEntity(userDto);
        User newUser = userRepository.save(user);
        UserDto userResponse = mapper.mapToDto(newUser);

        return userResponse;
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userById(id);
        return mapper.mapToDto(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = getUserByIdFromDatabase(id);
        userRepository.deleteById(Math.toIntExact(id));
    }

    private User userById(Long id) {

        User user = retrieveUserById(id);
        return user;
    }

    private User retrieveUserById(Long id) {

        return userRepository.findById(Math.toIntExact(id)).orElseThrow(
                () -> new ResourceNotFoundException("User", "userId", id));
    }


    private User getUserByIdFromDatabase(Long id) {

        return userRepository.findById(Math.toIntExact(id)).orElseThrow(() -> new ResourceNotFoundException("Todo", "id", id));
    }
}
