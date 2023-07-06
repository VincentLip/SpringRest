package com.example.tpdto.service.impl;

import com.example.tpdto.entity.Comment;
import com.example.tpdto.repository.CommentRepository;
import com.example.tpdto.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Override
    public List<Comment> findAll() {
        return null;
    }

    @Override
    public Optional<Comment> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Comment save(Comment comment) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
