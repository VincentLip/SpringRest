package com.example.tpdto.service;

import com.example.tpdto.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    List<Comment> findAll();
    Optional<Comment> findById(Integer id);

    Comment save(Comment comment);

    void deleteById(Integer id);
}
