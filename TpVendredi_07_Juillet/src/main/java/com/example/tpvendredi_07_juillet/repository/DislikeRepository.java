package com.example.tpvendredi_07_juillet.repository;

import com.example.tpvendredi_07_juillet.entity.Dislike;
import com.example.tpvendredi_07_juillet.entity.Like;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DislikeRepository extends CrudRepository<Dislike,Integer> {

    List<Dislike> findByCommentId(Integer id);
}
