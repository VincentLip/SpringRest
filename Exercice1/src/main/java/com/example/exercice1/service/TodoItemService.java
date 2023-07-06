package com.example.exercice1.service;

import com.example.exercice1.entity.TodoItem;
import com.example.exercice1.repository.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoItemService {

    @Autowired
    private TodoItemRepository todoItemRepository;


    public Boolean getTodoItemName(String name){
        TodoItem todoitem = todoItemRepository.findTodoitemByName(name);
        return todoitem != null ? true: false;
    }


    public TodoItem createTodoItem(TodoItem todoitem){
        return todoItemRepository.save(todoitem);
    }

    public List<TodoItem> getAllTodoitem(){
        return (List<TodoItem>) todoItemRepository.findAll();
    }

    public Optional<TodoItem> getTodoitemById(Integer id){
        return todoItemRepository.findById(id);
    }

    public void deleteTodoitem(Integer id){
        todoItemRepository.deleteById(id);
    }
}
