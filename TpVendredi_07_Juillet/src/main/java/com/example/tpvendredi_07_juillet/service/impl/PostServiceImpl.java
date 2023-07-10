package com.example.tpvendredi_07_juillet.service.impl;

import com.example.tpvendredi_07_juillet.dto.PostDto;
import com.example.tpvendredi_07_juillet.entity.Post;
import com.example.tpvendredi_07_juillet.entity.User;
import com.example.tpvendredi_07_juillet.exception.BlogApiException;
import com.example.tpvendredi_07_juillet.exception.ResourceNotFoundException;
import com.example.tpvendredi_07_juillet.repository.PostRepository;
import com.example.tpvendredi_07_juillet.repository.UserRepository;
import com.example.tpvendredi_07_juillet.service.PostService;
import com.example.tpvendredi_07_juillet.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {


    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Mapper mapper;


    @Override
    public PostDto createPost(Integer userId, PostDto postDto) {

        Post post = mapper.mapToEntity(postDto);

        User user = retrieveUserEntityById(userId);

        post.setUser(user);

        Post newPost = postRepository.save(post);
        return mapper.mapToDto(newPost);
    }

    @Override
    public List<PostDto> getPostsByUserId(Integer userId) {

        List<Post> posts = postRepository.findByUserId(userId);
        return posts.stream().map(post -> mapper.mapToDto(post)).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(Integer userId, Integer postId) {

        Post post = postById(userId,postId);
        return mapper.mapToDto(post);
    }

    @Override
    public PostDto updatePost(Integer userId, Integer postId, PostDto postRequest) {

        User user = retrieveUserEntityById(userId);
        Post post = retrievePostById(postId);

        badRequestException(post,user);

        post.setText(postRequest.getText());
        post.setTitle(postRequest.getTitle());

        Post updatePost = postRepository.save(post);

        return mapper.mapToDto(updatePost);
    }

    @Override
    public void deletePost(Integer userId, Integer postId) {

        Post post = postById(userId,postId);

        postRepository.delete(post);


    }


    private User retrieveUserEntityById(Integer userId) {

        return userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId));
    }

    private Post retrievePostById(Integer postId) {

        return postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "postId", postId));
    }

    private Post postById(Integer userId, Integer postId) {

        User user = retrieveUserEntityById(userId);
        Post post = retrievePostById(postId);

        badRequestException(post, user);

        return post;
    }

    private void badRequestException(Post post, User user) {

        if (((post.getUser().getId()) != (user.getId()))) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Post does not belong to user");
        }

    }
}
