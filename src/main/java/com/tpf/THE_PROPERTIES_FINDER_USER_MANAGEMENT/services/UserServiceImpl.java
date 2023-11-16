package com.tpf.THE_PROPERTIES_FINDER_USER_MANAGEMENT.services;

import com.tpf.THE_PROPERTIES_FINDER_USER_MANAGEMENT.facade.UserHelper;
import com.tpf.THE_PROPERTIES_FINDER_USER_MANAGEMENT.models.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserHelper userHelper;

    @Override
    public User saveUser(User user){
        return userHelper.saveUser(user);
    }

    @Override
    public ResponseEntity<User> getUserByUserId(Long userId){
        return userHelper.getUserByUserId(userId);
    }

    @Override
    public ResponseEntity<List<User>> getAllUsers(){
        return userHelper.getAllUsers();
    }

    @Override
    public void deleteUserById(Long userId){
        userHelper.deleteUserById(userId);
    }

    @Override
    public ResponseEntity<User> updateUserById(Long userId, User updatedUser){
        return userHelper.updateUserById(userId, updatedUser);
    }

}
