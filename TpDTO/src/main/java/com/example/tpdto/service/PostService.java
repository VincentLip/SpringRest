package com.example.tpdto.service;

import com.example.tpdto.entity.Comment;
import com.example.tpdto.entity.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {

    List<Post> findAll();
    Optional<Post> findById(Integer id);

    Post save(Post post);

    void deleteById(Integer id);
}
