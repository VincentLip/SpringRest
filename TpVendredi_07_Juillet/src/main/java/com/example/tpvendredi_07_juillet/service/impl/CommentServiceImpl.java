package com.example.tpvendredi_07_juillet.service.impl;

import com.example.tpvendredi_07_juillet.dto.CommentDto;
import com.example.tpvendredi_07_juillet.entity.Comment;
import com.example.tpvendredi_07_juillet.entity.Post;
import com.example.tpvendredi_07_juillet.exception.BlogApiException;
import com.example.tpvendredi_07_juillet.exception.ResourceNotFoundException;
import com.example.tpvendredi_07_juillet.repository.CommentRepository;
import com.example.tpvendredi_07_juillet.repository.PostRepository;
import com.example.tpvendredi_07_juillet.service.CommentService;
import com.example.tpvendredi_07_juillet.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private Mapper mapper;


    @Override
    public CommentDto createComment(Integer postId, CommentDto commentDto) {
        Comment comment = mapper.mapToEntity(commentDto);

        Post post = retrievePostEntityById(postId);

        comment.setPost(post);

        Comment newComment =  commentRepository.save(comment);

        return mapper.mapToDto(newComment);
    }

    @Override
    public List<CommentDto> getPostsByCommentId(Integer commentId) {
        List<Comment> comments = commentRepository.findByPostId(commentId);

        //convert list of comment entities to list of comment
        return comments.stream().map(post -> mapper.mapToDto(post)).collect(Collectors.toList());

    }

    @Override
    public CommentDto getCommentById(Integer postId, Integer commentId) {
        Comment comment = commentById(postId, commentId);

        return mapper.mapToDto(comment);
    }

    @Override
    public CommentDto updateComment(Integer postId, Integer commentId, CommentDto commentRequest) {
        Post post = retrievePostEntityById(postId);

        Comment comment = retrieveCommentById(commentId);

        badRequestException(comment, post);

        comment.setMessage(commentRequest.getMessage());


        Comment updatedComment = commentRepository.save(comment);

        return mapper.mapToDto(updatedComment);
    }

    @Override
    public void deleteComment(Integer postId, Integer commentId) {

        Comment comment = commentById(postId, commentId);

        commentRepository.delete(comment);

    }

    private Comment commentById(Integer postId, Integer commentId) {

        Post post = retrievePostEntityById(postId);

        Comment comment = retrieveCommentById(commentId);

        badRequestException(comment, post);

        return comment;
    }


    private void badRequestException(Comment comment, Post post) {

        if (((comment.getPost().getId()) != (post.getId()))) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

    }

    private Post retrievePostEntityById(Integer postId) {

        return postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));
    }

    private Comment retrieveCommentById(Integer commentId) {

        return commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "commentId", commentId));
    }
}
