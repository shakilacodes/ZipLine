package com.ziplinegreen.vault.Controller;

import com.ziplinegreen.vault.Model.Post;
import com.ziplinegreen.vault.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@CrossOrigin
@RestController
public class PostController {

    private PostService postService;
    private UserController userController;


    @Autowired
    public PostController(PostService postService, UserController userController) {
        this.postService = postService;
        this.userController = userController;
    }


    @MessageMapping("/posts")
    @SendTo("/topic/posts")
    public Post createPost(@RequestBody Post post) {
        post.setUserName(userController.getUserById(post.getUserId()).getUsername());
        return postService.createPost(post);
    }


//    @GetMapping("/posts/all")
//    public Iterable<Post> getAllPosts() {
//        return postService.getAllPosts();
//    }

    @GetMapping("/posts/all")
    public Page<Post> pageAllPosts(Pageable pageable) {
       return postService.listAllPostsByPageMostRecentFirst(pageable);
    }

    @GetMapping("/posts/id/{userId}")
    public Page<Post> getAllPostsByUserId(@PathVariable Long userId, Pageable pageable) {
        return postService.findByUserId(userId, pageable);
    }

    @DeleteMapping("/posts/delete")
    public ResponseEntity deletePost(@RequestBody Long postId) {
        return postService.deletePost(postId);
    }

    @DeleteMapping("/posts/deleteAll")
    public ResponseEntity deleteAllPosts(@RequestBody Long userId) {
        return postService.deleteAllPosts(userId);
    }

    @PutMapping("/posts/update/{postId}")
    public Post updatePost(@PathVariable Long postId, @RequestBody String message) {
        return postService.updatePost(postId,message);
    }

}
