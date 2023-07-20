package com.example.tp_spring_security.utils;

import com.example.tp_spring_security.dto.TodoDto;
import com.example.tp_spring_security.dto.UserDto;
import com.example.tp_spring_security.entity.Todo;
import com.example.tp_spring_security.entity.User;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

@Component
public class Mapper {

    public TodoDto mapToDto(Todo todo) {

        ModelMapper mapper = new ModelMapper();
        TodoDto todoDto = mapper.map(todo, TodoDto.class);
        return todoDto;
    }

    public Todo mapToEntity( TodoDto todoDto) {
        ModelMapper mapper = new ModelMapper();
        Todo todo = mapper.map(todoDto, Todo.class);

        return todo;
    }

    public UserDto mapToDto(User user) {

        ModelMapper mapper = new ModelMapper();
        UserDto userDto = mapper.map(user, UserDto.class);
        return userDto;
    }

    public User mapToEntity( UserDto userDto) {
        ModelMapper mapper = new ModelMapper();
        User user = mapper.map(userDto, User.class);

        return user;
    }
}
