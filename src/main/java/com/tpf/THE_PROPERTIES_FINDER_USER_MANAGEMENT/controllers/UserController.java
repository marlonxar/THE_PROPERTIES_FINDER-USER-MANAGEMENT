package com.tpf.THE_PROPERTIES_FINDER_USER_MANAGEMENT.controllers;

import com.tpf.THE_PROPERTIES_FINDER_USER_MANAGEMENT.models.User;
import com.tpf.THE_PROPERTIES_FINDER_USER_MANAGEMENT.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-management")
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<User> getUserByUserId(@PathVariable Long userId) {
        return userService.getUserByUserId(userId);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/user/{userId}")
    public void deleteUserById(@PathVariable Long userId){
        userService.deleteUserById(userId);
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<User> updateUserById(@PathVariable Long userId, @RequestBody User updatedUser){
        return userService.updateUserById(userId, updatedUser);
    }

}
