package com.example.tpvendredi_07_juillet.service.impl;

import com.example.tpvendredi_07_juillet.dto.DislikeDto;
import com.example.tpvendredi_07_juillet.entity.Comment;
import com.example.tpvendredi_07_juillet.entity.Dislike;
import com.example.tpvendredi_07_juillet.entity.Like;
import com.example.tpvendredi_07_juillet.exception.BlogApiException;
import com.example.tpvendredi_07_juillet.exception.ResourceNotFoundException;
import com.example.tpvendredi_07_juillet.repository.CommentRepository;
import com.example.tpvendredi_07_juillet.repository.DislikeRepository;
import com.example.tpvendredi_07_juillet.repository.LikeRepository;
import com.example.tpvendredi_07_juillet.service.DislikeService;
import com.example.tpvendredi_07_juillet.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DislikeServiceImpl implements DislikeService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private DislikeRepository dislikeRepository;

    @Autowired
    private Mapper mapper;

    @Override
    public DislikeDto createDislike(Integer commentId, DislikeDto dislikeDto) {
        Dislike dislike = mapper.mapToEntity(dislikeDto);

        Comment comment = retrieveCommentById(commentId);

        dislike.setComment(comment);

        Dislike newDislike = dislikeRepository.save(dislike);
        return mapper.mapToDto(newDislike);
    }

    @Override
    public List<DislikeDto> getPostsByDislikeId(Integer dislikeId) {
        List<Dislike> dislikes = dislikeRepository.findByCommentId(dislikeId);

        return dislikes.stream().map(dislike -> mapper.mapToDto(dislike)).collect(Collectors.toList());

    }

    @Override
    public DislikeDto getDislikeById(Integer commentId, Integer dislikeId) {
        Dislike dislike = dislikeById(commentId, dislikeId);

        return mapper.mapToDto(dislike);
    }

    @Override
    public DislikeDto updateDislike(Integer commentId, Integer dislikeId, DislikeDto dislikeRequest) {
        Dislike dislike  = retrieveDislikeEntityById(dislikeId);
        Comment comment = retrieveCommentById(commentId);

        badRequestException(dislike, comment);

        dislike.setDate(dislikeRequest.getDate());

        Dislike updatedDislike = dislikeRepository.save(dislike);

        return mapper.mapToDto(updatedDislike);
    }

    @Override
    public void deleteDislike(Integer commentId, Integer dislikeId) {

        Dislike dislike  = retrieveDislikeEntityById(dislikeId);

        dislikeRepository.delete(dislike);

    }

    private Dislike dislikeById(Integer commentId, Integer dislikeId) {

        Comment comment = retrieveCommentById(commentId);

        Dislike dislike = retrieveDislikeEntityById(dislikeId);

        badRequestException(dislike, comment);

        return dislike;
    }


    private void badRequestException(Dislike dislike, Comment comment) {

        if (((dislike.getComment().getId()) != (comment.getId()))) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Dislike does not belong to comment");
        }

    }

    private Dislike retrieveDislikeEntityById(Integer dislikeId) {

        return dislikeRepository.findById(dislikeId).orElseThrow(
                () -> new ResourceNotFoundException("Disike", "id", dislikeId));
    }

    private Comment retrieveCommentById(Integer commentId) {

        return commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "commentId", commentId));
    }
}
