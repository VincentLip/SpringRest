package com.example.tpdto.controller;

import com.example.tpdto.dto.*;
import com.example.tpdto.entity.Post;
import com.example.tpdto.service.CommentService;
import com.example.tpdto.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostDtoToPost postDtoToPost;

    @Autowired
    private PostToPostDto postToPostDto;


    @PostMapping("/create_post")
    public ResponseEntity<PostDto> addPost(@RequestBody Post post){

        Post post1 = postService.save(post);
        return new ResponseEntity<PostDto>(postToPostDto.convert(post1), HttpStatus.OK);

    }

    @GetMapping("/post")
    public ResponseEntity<List<PostDto>> getAllPostDto(){
        List <PostDto> postDtos = new ArrayList<>();
        for (Post post : postService.findAll()){
            postDtos.add(postToPostDto.convert(post));
        }
        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable Integer id){
        Optional<Post> post = postService.findById(id);
        if(post.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<PostDto>(postToPostDto.convert(post.get()), HttpStatus.OK);
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<PostDto> deleteEmployeeById(@PathVariable Integer id) {
        try {
            postService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/post/{id}")
    public ResponseEntity<PostDto> updateEmployee(@RequestBody PostDto postDto, @PathVariable Integer id){
        postDto.setId(id);
        Post post =  postService.save(postDtoToPost.convert(postDto));

        return new ResponseEntity<>(postToPostDto.convert(post),HttpStatus.ACCEPTED);

    }

}
