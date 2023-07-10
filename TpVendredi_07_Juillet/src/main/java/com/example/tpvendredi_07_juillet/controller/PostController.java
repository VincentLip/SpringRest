package com.example.tpvendredi_07_juillet.controller;

import com.example.tpvendredi_07_juillet.dto.PostDto;
import com.example.tpvendredi_07_juillet.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/users/{userId}/posts")
    public ResponseEntity<PostDto> createPost(@PathVariable(value = "userId") Integer userId,
                                                 @Valid @RequestBody PostDto postDto) {
        System.out.println(postDto);

        return new ResponseEntity<>(postService.createPost(userId, postDto), HttpStatus.CREATED);
    }

    @GetMapping("/users/{userId}/posts")
    public List<PostDto> getPostsByPostId(@PathVariable(value = "userId") Integer userId) {

        return postService.getPostsByUserId(userId);
    }

    @GetMapping("/users/{userId}/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(value = "userId") Integer userId,
                                                     @PathVariable(value = "postId") Integer postId) {
        PostDto postDto = postService.getPostById(userId, postId);
        return new ResponseEntity<>(postDto, HttpStatus.OK);

    }

    @PutMapping("/users/{userId}/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(value = "userId") Integer userId,
                                                  @PathVariable(value = "postId") Integer postId,
                                                  @RequestBody PostDto postDto) {
        PostDto updatedPostDto = postService.updatePost(userId, postId,postDto);
        return new ResponseEntity<>(updatedPostDto, HttpStatus.OK);
    }

    @DeleteMapping("/users/{userId}/posts/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable(value = "userId") Integer userId,
                                             @PathVariable(value = "postId") Integer postId) {

        postService.deletePost(userId, postId);
        return new ResponseEntity<>("Post deleted succcessfully", HttpStatus.OK);
    }

}
