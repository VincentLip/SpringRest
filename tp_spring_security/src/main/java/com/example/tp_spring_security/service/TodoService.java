package com.example.tp_spring_security.service;

import com.example.tp_spring_security.dto.TodoDto;

import java.util.List;

public interface TodoService {

    TodoDto createTodo(TodoDto todoDto);

    TodoDto getTodoById(Long id);

    List<TodoDto> getAllTodos();

    TodoDto updateTodo(TodoDto todoDto, Long id);

    void deleteTodo( Long id);
}
