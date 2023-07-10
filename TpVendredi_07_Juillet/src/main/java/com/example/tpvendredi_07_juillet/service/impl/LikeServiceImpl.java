package com.example.tpvendredi_07_juillet.service.impl;

import com.example.tpvendredi_07_juillet.dto.LikeDto;
import com.example.tpvendredi_07_juillet.entity.Comment;
import com.example.tpvendredi_07_juillet.entity.Like;
import com.example.tpvendredi_07_juillet.exception.BlogApiException;
import com.example.tpvendredi_07_juillet.exception.ResourceNotFoundException;
import com.example.tpvendredi_07_juillet.repository.CommentRepository;
import com.example.tpvendredi_07_juillet.repository.LikeRepository;
import com.example.tpvendredi_07_juillet.service.LikeService;
import com.example.tpvendredi_07_juillet.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private Mapper mapper;


    @Override
    public LikeDto createLike(Integer commentId, LikeDto likeDto) {

        Like like = mapper.mapToEntity(likeDto);

        Comment comment = retrieveCommentById(commentId);

        like.setComment(comment);

        Like newLike = likeRepository.save(like);
        return mapper.mapToDto(newLike);
    }

    @Override
    public List<LikeDto> getPostsByLikeId(Integer likeId) {
        List<Like> likes = likeRepository.findByCommentId(likeId);

        return likes.stream().map(like -> mapper.mapToDto(like)).collect(Collectors.toList());

    }

    @Override
    public LikeDto getLikeById(Integer commentId, Integer likeId) {
        Like like = likeById(commentId, likeId);

        return mapper.mapToDto(like);
    }

    @Override
    public LikeDto updateLike(Integer commentId, Integer likeId, LikeDto likeRequest) {
        Like like = retrieveLikeEntityById(likeId);
        Comment comment = retrieveCommentById(commentId);

        badRequestException(like, comment);

        like.setDate(likeRequest.getDate());

        Like updatedLike = likeRepository.save(like);

        return mapper.mapToDto(updatedLike);
    }

    @Override
    public void deleteLike(Integer commentId, Integer likeId) {

        Like like = likeById(commentId, likeId);

        likeRepository.delete(like);

    }


    private Like likeById(Integer commentId, Integer likeId) {

        Comment comment = retrieveCommentById(commentId);

        Like like = retrieveLikeEntityById(likeId);

        badRequestException(like, comment);

        return like;
    }


    //badRequestException IF ELSE
    private void badRequestException(Like like, Comment comment) {

        if (((like.getComment().getId()) != (comment.getId()))) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Like does not belong to comment");
        }

    }

    //retrieve post entity by id
    private Like retrieveLikeEntityById(Integer likeId) {

        return likeRepository.findById(likeId).orElseThrow(
                () -> new ResourceNotFoundException("Like", "id", likeId));
    }

    //retrieve comment by id
    private Comment retrieveCommentById(Integer commentId) {

        return commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "commentId", commentId));
    }
}
