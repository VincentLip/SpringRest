package com.example.tpvendredi_07_juillet.controller;


import com.example.tpvendredi_07_juillet.dto.CommentDto;
import com.example.tpvendredi_07_juillet.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CommentController {

    @Autowired
    private CommentService commentService;


    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") Integer postId,
                                                    @Valid @RequestBody CommentDto commentDto) {
        System.out.println(commentDto);

        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);

    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable(value = "postId") Integer postId) {

        return commentService.getPostsByCommentId(postId);
    }

    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "postId") Integer postId,
                                                     @PathVariable(value = "commentId") Integer commentId) {
        CommentDto commentDto = commentService.getCommentById(postId, commentId);
        return new ResponseEntity<>(commentDto, HttpStatus.OK);

    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(value = "postId") Integer postId,
                                                    @PathVariable(value = "commentId") Integer commentId,
                                                    @RequestBody CommentDto commentDto) {
        CommentDto updatedCommentDto = commentService.updateComment(postId, commentId, commentDto);
        return new ResponseEntity<>(updatedCommentDto, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable(value = "postId") Integer postId, @PathVariable(value = "commentId") Integer commentId) {

        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>("Comment deleted succcessfully", HttpStatus.OK);
    }
}
