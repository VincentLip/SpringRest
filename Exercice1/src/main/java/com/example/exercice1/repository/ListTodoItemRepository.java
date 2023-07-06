package com.example.exercice1.repository;

import com.example.exercice1.entity.ListTodoItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListTodoItemRepository extends CrudRepository<ListTodoItem,Integer> {

    ListTodoItem findListtodoitemByTitle(String title);
}
