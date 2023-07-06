package com.example.tpdto.dto;

import com.example.tpdto.entity.Comment;
import com.example.tpdto.entity.Post;
import com.example.tpdto.service.CommentService;
import com.example.tpdto.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostDtoToPost {
    @Autowired
    PostService postService;

    public Post convert(PostDto postDto) {

        Post post = postDto.getId() != null ? postService.findById(postDto.getId()).get() : new Post();

        post.setComments(postDto.getComments());
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        return post;
    }

}
