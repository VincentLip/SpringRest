package com.example.tp_spring_security.repository;

import com.example.tp_spring_security.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {

    User findByUsername (String username);
}
