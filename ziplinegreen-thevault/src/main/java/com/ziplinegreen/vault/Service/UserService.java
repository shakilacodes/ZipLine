package com.ziplinegreen.vault.Service;

import com.ziplinegreen.vault.Model.User;
import com.ziplinegreen.vault.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UsersRepository userRepository;
    private PostService postService;
    //private PostController postController;

    @Autowired
    public UserService(UsersRepository userRepository, PostService postService) {
        this.userRepository = userRepository;
        this.postService = postService;
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public User findUserById(Long id){
        return userRepository.findById(id).get();
    }

    public User findUserByUsername(String username) { return userRepository.findUserByUsername(username);}

    public User updateUsername(Long userId, String username){
        User user = findUserById(userId);
        user.setUsername(username);
        return userRepository.save(user);
    }

    public ResponseEntity deleteUser(Long userId){
        postService.deleteAllPosts(userId);
        userRepository.delete(userRepository.findById(userId).get());
        return new ResponseEntity(HttpStatus.OK);
    }
}


