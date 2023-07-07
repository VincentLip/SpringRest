package com.example.tpvendredi_07_juillet.service;

import com.example.tpvendredi_07_juillet.dto.PostDto;

import java.util.List;

public interface PostService {

    PostDto createPost (Integer userId, PostDto postDto);

    List<PostDto> getPostsByUserId(Integer userId);

    PostDto getPostById(Integer userId , Integer postId);

    PostDto updatePost(Integer userId , Integer postId , PostDto postRequest);

    void deletePost( Integer userId, Integer postId);
}
