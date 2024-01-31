package com.vn.controller;

import com.vn.entity.UserEntity;
import com.vn.model.User;
import com.vn.model.UserDetail;
import com.vn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/add")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/user/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/user/search/{searchTerm}")
    public ResponseEntity<User> getUserByEmail(@PathVariable("searchTerm") String searchTerm) {
        User user1 = null;
        User user2 = null;
        try{
            user1 = userService.findByEmail(searchTerm);
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            user2 = userService.findByPhoneNumber(searchTerm);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (user1 != null) {
            return ResponseEntity.ok(user1);
        } else if (user2 != null) {
            return ResponseEntity.ok(user2);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/user/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        user = userService.updateUser(id, user);
        return ResponseEntity.ok(user);
    }
}
