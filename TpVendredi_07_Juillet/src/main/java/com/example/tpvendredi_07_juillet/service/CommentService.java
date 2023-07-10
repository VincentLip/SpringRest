package com.example.tpvendredi_07_juillet.service;

import com.example.tpvendredi_07_juillet.dto.CommentDto;
import com.example.tpvendredi_07_juillet.dto.LikeDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment (Integer postId, CommentDto commentDto);

    List<CommentDto> getPostsByCommentId(Integer commentId);

    CommentDto getCommentById(Integer postId , Integer commentId);

    CommentDto updateComment(Integer postId , Integer commentId , CommentDto commentRequest);

    void deleteComment( Integer postId, Integer commentId);
}
