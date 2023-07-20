package com.example.tp_spring_security.repository;

import com.example.tp_spring_security.entity.Todo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends CrudRepository<Todo,Integer> {
    List<Todo> findById(Long id);
}
