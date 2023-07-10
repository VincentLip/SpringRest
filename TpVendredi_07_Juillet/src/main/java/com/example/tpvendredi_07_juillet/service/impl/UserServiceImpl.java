package com.example.tpvendredi_07_juillet.service.impl;

import com.example.tpvendredi_07_juillet.dto.UserDto;
import com.example.tpvendredi_07_juillet.entity.User;
import com.example.tpvendredi_07_juillet.exception.ResourceNotFoundException;
import com.example.tpvendredi_07_juillet.repository.UserRepository;
import com.example.tpvendredi_07_juillet.service.UserService;
import com.example.tpvendredi_07_juillet.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<UserDto> getAllUsers() {

        List<User> users = (List<User>) userRepository.findAll();
        List<UserDto> userReadDtoList = users.stream().map(user->mapper.mapToDto(user)).collect(Collectors.toList());
        return userReadDtoList;
    }

    @Override
    public UserDto getUserById(Integer id) {

        User user = getUserByIdFromDatabase(id);
        return mapper.mapToDto(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer id) {

        User user = getUserByIdFromDatabase(id);

        user.setUserName(userDto.getUsername());

        User updateUser = userRepository.save(user);

        return mapper.mapToDto(updateUser);
    }

    @Override
    public void deleteUserById(Integer id) {

        User user = getUserByIdFromDatabase(id);
        userRepository.deleteById(id);

    }

    private User getUserByIdFromDatabase(Integer id) {

        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }
}
