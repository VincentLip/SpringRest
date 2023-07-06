package com.example.tpdto.dto;

import com.example.tpdto.entity.Post;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PostToPostDto {

    public PostDto convert(Post post){
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setComments(post.getComments());
        postDto.setContent(post.getComments().toString());
        postDto.setDescription(post.getDescription());
        postDto.setTitle(post.getTitle());
        postDto.setDate(new Date());

        return postDto;
    }
}
