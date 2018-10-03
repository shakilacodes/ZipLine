package com.ziplinegreen.vault.Controller;

import com.ziplinegreen.vault.Model.User;
import com.ziplinegreen.vault.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable Long userId) {

        return userService.findUserById(userId);
    }
    @CrossOrigin
    @GetMapping("/users/{username}")
    public User getUserById(@PathVariable String username) {

        return userService.findUserByUsername(username);
    }
    @CrossOrigin
    @PutMapping("/users/{userId}")
    public User updateUsername(@PathVariable Long userId, @RequestBody String username) {
        return userService.updateUsername(userId, username);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        return userService.deleteUser(userId);
    }
}