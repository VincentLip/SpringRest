package com.example.tpvendredi_07_juillet.repository;

import com.example.tpvendredi_07_juillet.entity.Like;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends CrudRepository<Like,Integer> {
}
