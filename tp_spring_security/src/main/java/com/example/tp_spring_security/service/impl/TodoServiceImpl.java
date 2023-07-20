package com.example.tp_spring_security.service.impl;

import com.example.tp_spring_security.dto.TodoDto;
import com.example.tp_spring_security.entity.Todo;
import com.example.tp_spring_security.exception.ResourceNotFoundException;
import com.example.tp_spring_security.repository.TodoRepository;
import com.example.tp_spring_security.service.TodoService;
import com.example.tp_spring_security.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private Mapper mapper;


    @Override
    public TodoDto createTodo(TodoDto todoDto) {
        Todo todo = mapper.mapToEntity(todoDto);
        Todo newTodo = todoRepository.save(todo);
        TodoDto todoResponse = mapper.mapToDto(newTodo);

        return todoResponse;
    }

    @Override
    public TodoDto getTodoById(Long id) {
        Todo todo = todoById(id);
        return mapper.mapToDto(todo);
    }

    @Override
    public List<TodoDto> getAllTodos() {
        List<Todo> todos = (List<Todo>) todoRepository.findAll();
        List<TodoDto> todoDtoList = todos.stream().map(todo->mapper.mapToDto(todo)).collect(Collectors.toList());
        return todoDtoList;
    }

    @Override
    public TodoDto updateTodo(TodoDto todoDto, Long id) {
        Todo todo = getTodoByIdFromDatabase(id);

        todo.setDescription(todoDto.getDescription());
        todo.setTitle(todoDto.getTitle());
        todo.setCompleted(todoDto.isCompleted());

        Todo updateTodo = todoRepository.save(todo);

        return mapper.mapToDto(updateTodo);
    }

    @Override
    public void deleteTodo(Long id) {
        Todo todo = getTodoByIdFromDatabase(id);
        todoRepository.deleteById(Math.toIntExact(id));
    }

    private Todo retrieveTodoById(Long id) {

        return todoRepository.findById(Math.toIntExact(id)).orElseThrow(
                () -> new ResourceNotFoundException("Todo", "todoId", id));
    }

    private Todo todoById(Long id) {

        Todo todo = retrieveTodoById(id);
        return todo;
    }

    private Todo getTodoByIdFromDatabase(Long id) {

        return todoRepository.findById(Math.toIntExact(id)).orElseThrow(() -> new ResourceNotFoundException("Todo", "id", id));
    }


}
