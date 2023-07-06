package com.example.exercice1.controller;

import com.example.exercice1.entity.TodoItem;
import com.example.exercice1.service.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("todoitem")
public class TodoItemController {

    @Autowired
    private TodoItemService todoItemService;


    @PostMapping("/created")
    public ResponseEntity<String> post(@RequestBody TodoItem todoitem) {

        if (todoItemService.getTodoItemName(todoitem.getName())) {
            String errorMessage = "La todo existe déjà";
            return ResponseEntity.badRequest().body(errorMessage);
        }

        if (todoitem == null || todoitem.getName() == null || todoitem.getDate() == null) {
            String message = "Requête invalide : le nom et la date sont requis";
            return ResponseEntity.badRequest().body(message);
        }

        todoItemService.createTodoItem(todoitem);
        return ResponseEntity.status(HttpStatus.CREATED).body("Todo créée avec succès");

    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllPersonne() {
        List<TodoItem> todoItems = todoItemService.getAllTodoitem();
        String message = "La liste est vide";

        if (todoItems.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(message);
        } else {
            return ResponseEntity.ok(todoItems);
        }

    }
}
