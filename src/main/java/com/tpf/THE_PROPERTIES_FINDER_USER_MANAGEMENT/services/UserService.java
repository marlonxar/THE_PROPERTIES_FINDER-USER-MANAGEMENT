package com.tpf.THE_PROPERTIES_FINDER_USER_MANAGEMENT.services;

import com.tpf.THE_PROPERTIES_FINDER_USER_MANAGEMENT.models.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    ResponseEntity<User> getUserByUserId(Long userId);

    ResponseEntity<List<User>> getAllUsers();

    void deleteUserById(Long userId);

    ResponseEntity<User> updateUserById(Long userId, User updatedUser);
}
