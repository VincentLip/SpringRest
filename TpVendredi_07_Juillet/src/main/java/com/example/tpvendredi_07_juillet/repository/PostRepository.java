package com.example.tpvendredi_07_juillet.repository;

import com.example.tpvendredi_07_juillet.entity.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post,Integer> {

    List<Post> findByUserId(Integer id);
}
