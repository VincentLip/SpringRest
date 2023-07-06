package com.example.tpdto.dto;

import com.example.tpdto.entity.Comment;
import com.example.tpdto.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentDtoToComment {

    @Autowired
    CommentService commentService;

    public Comment convert(CommentDto commentDto) {

        Comment comment = commentDto.getId() != null ? commentService.findById(commentDto.getId()).get() : new Comment();

        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        comment.setName(commentDto.getName());
        comment.setPosts(commentDto.getPost());

        return comment;
    }
}
