package com.example.tpvendredi_07_juillet.repository;

import com.example.tpvendredi_07_juillet.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
}
