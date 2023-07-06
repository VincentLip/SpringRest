package com.example.exercice1.repository;

import com.example.exercice1.entity.TodoItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoItemRepository extends CrudRepository<TodoItem,Integer> {

    TodoItem findTodoitemByName(String name);
}
