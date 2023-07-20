package com.example.tp_spring_security.controller;

import com.example.tp_spring_security.dto.TodoDto;
import com.example.tp_spring_security.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping("/todos")
    public ResponseEntity<TodoDto> createTodo(@Valid @RequestBody TodoDto todoDto){
        return new ResponseEntity<>(todoService.createTodo(todoDto), HttpStatus.CREATED);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<TodoDto>> getAllTodos(){
        return new ResponseEntity(todoService.getAllTodos(), HttpStatus.OK);
    }

    @PutMapping("/todos/{todoId}")
    public ResponseEntity<TodoDto> getTodoById(@PathVariable(value = "todoId") Integer todoId,
                                                           @RequestBody TodoDto todoDto) {
        TodoDto updatedTodoDto = todoService.updateTodo(todoDto, Long.valueOf(todoId));
        return new ResponseEntity<>(updatedTodoDto, HttpStatus.OK);
    }

    @DeleteMapping("/todos/{todoId}")
    public ResponseEntity<String> deleteTodo(@PathVariable(value = "todoId") Integer todoId) {
        todoService.deleteTodo(Long.valueOf(todoId));
        return new ResponseEntity<>("Todo deleted succcessfully", HttpStatus.OK);
    }
}
