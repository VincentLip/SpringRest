package com.example.tpdto.dto;

import com.example.tpdto.entity.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentToCommentDto {

    public CommentDto convert(Comment comment){
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());
        commentDto.setPost(commentDto.getPost());

        return commentDto;
    }
}
