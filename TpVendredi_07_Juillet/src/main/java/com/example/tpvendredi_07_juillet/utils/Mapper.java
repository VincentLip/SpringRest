package com.example.tpvendredi_07_juillet.utils;

import com.example.tpvendredi_07_juillet.dto.*;
import com.example.tpvendredi_07_juillet.entity.*;
import org.modelmapper.ModelMapper;

public class Mapper {

    public UserDto mapToDto(User user) {

        ModelMapper mapper = new ModelMapper();
        UserDto userDto = mapper.map(user, UserDto.class);
        return userDto;

    }

    public User mapToEntity( UserDto userDto) {
        ModelMapper mapper = new ModelMapper();
        User user = mapper.map(userDto, User.class);

        return user;
    }


    public PostDto mapToDto(Post post) {

        ModelMapper mapper = new ModelMapper();
        PostDto postDto = mapper.map(post, PostDto.class);
        return postDto;

    }

    public Post mapToEntity(PostDto postDto) {
        ModelMapper mapper = new ModelMapper();
        //map with modelMapper lib.
        Post post = mapper.map(postDto, Post.class);

        return post;
    }

    public CommentDto mapToDto(Comment comment) {

        ModelMapper mapper = new ModelMapper();
        CommentDto commentDto = mapper.map(comment, CommentDto.class);
        return commentDto;

    }

    public Comment mapToEntity(CommentDto commentDto) {
        ModelMapper mapper = new ModelMapper();
        Comment comment = mapper.map(commentDto, Comment.class);

        return comment;
    }


    public LikeDto mapToDto(Like like) {

        ModelMapper mapper = new ModelMapper();
        LikeDto likeDto = mapper.map(like, LikeDto.class);
        return likeDto;

    }

    public Like mapToEntity(LikeDto likeDto) {
        ModelMapper mapper = new ModelMapper();
        Like like = mapper.map(likeDto, Like.class);

        return like;
    }


    public DislikeDto mapToDto(Dislike dislike) {

        ModelMapper mapper = new ModelMapper();
        DislikeDto dislikeDto = mapper.map(dislike, DislikeDto.class);
        return dislikeDto;

    }

    public Dislike mapToEntity(DislikeDto dislikeDto) {
        ModelMapper mapper = new ModelMapper();
        Dislike dislike = mapper.map(dislikeDto, Dislike.class);

        return dislike;
    }
}
