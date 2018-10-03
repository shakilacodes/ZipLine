package com.ziplinegreen.vault.Service;


import com.ziplinegreen.vault.Model.Post;
import com.ziplinegreen.vault.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class PostService {


    private PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createPost(Post post){
        return postRepository.save(post);
    }

    public Iterable<Post> getAllPosts(){
        return postRepository.findAll();
    }

    public ResponseEntity deletePost(Long postId){
        postRepository.delete(postRepository.findById(postId).get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity deleteAllPosts(Long userId){
        Iterable<Post> posts = postRepository.findByUserId(userId);
        for(Post post: posts){
            deletePost(post.getId());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public Page<Post> findByUserId(Long userId, Pageable pageable) {
        Iterable<Post> posts = postRepository.findByUserId(userId);
        List<Post> postList = StreamSupport.stream(posts.spliterator(), false)
                .collect(Collectors.toList());
        Collections.reverse(postList);

        int start = (int)pageable.getOffset();
        int end = (start + pageable.getPageSize()) > postList.size() ? postList.size() : (start + pageable.getPageSize());

        return new PageImpl<Post>(postList.subList(start,end),pageable, postList.size());
    }



    public Post updatePost(Long postId, String message){
        Post post = postRepository.findById(postId).get();
        post.setMessage(message);
        return postRepository.save(post);
    }

    public Page<Post> listAllByPage(Pageable pageable) {
       return postRepository.findAll(pageable);
    }

    public Page<Post> listAllPostsByPageMostRecentFirst(Pageable pageable){
        Iterable<Post> posts = getAllPosts();
        List<Post> postList = StreamSupport.stream(posts.spliterator(), false)
                .collect(Collectors.toList());
        Collections.reverse(postList);

        int start = (int)pageable.getOffset();
        int end = (start + pageable.getPageSize()) > postList.size() ? postList.size() : (start + pageable.getPageSize());

        return new PageImpl<Post>(postList.subList(start,end),pageable, postList.size());
    }
}

