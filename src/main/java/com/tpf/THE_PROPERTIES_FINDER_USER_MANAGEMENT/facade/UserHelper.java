package com.tpf.THE_PROPERTIES_FINDER_USER_MANAGEMENT.facade;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.tpf.THE_PROPERTIES_FINDER_USER_MANAGEMENT.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserHelper {

    private final String pathFile = "src/main/resources/users.json";

    public User saveUser(User user) {
        List<User> users = readUsersFromJson();
        Long newUserId = getNextUserId(users);
        user.setId(newUserId);
        users.add(user);
        writeUsersToJson(users);
        return user;
    }

    private Long getNextUserId(List<User> users) {
        if (users.isEmpty()) {
            return 1L;
        } else {
            return users.stream()
                    .mapToLong(User::getId)
                    .max()
                    .orElse(0L) + 1;
        }
    }

    private void writeUsersToJson(List<User> users) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            String usersJson = objectMapper.writeValueAsString(users);

            Files.writeString(Paths.get(pathFile), usersJson, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<User> readUsersFromJson() {
        try {
            if (Files.exists(Paths.get(pathFile))) {
                String jsonContent = new String(Files.readAllBytes(Paths.get(pathFile)));

                if (jsonContent.trim().isEmpty()) {
                    return new ArrayList<>();
                }

                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(jsonContent, objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));
            } else {
                return new ArrayList<>();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


    public ResponseEntity<User> getUserByUserId(Long userId) {
        List<User> users = readUsersFromJson();
        Optional<User> userOptional = users.stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst();

        return userOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = readUsersFromJson();
        return ResponseEntity.ok(users);
    }

    public void deleteUserById(Long userId) {
        List<User> users = readUsersFromJson();

        int indexToRemove = -1;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(userId)) {
                indexToRemove = i;
                break;
            }
        }

        if (indexToRemove != -1) {
            users.remove(indexToRemove);
            writeUsersToJson(users);
        }
    }

    public ResponseEntity<User> updateUserById(Long userId, User updatedUser) {
        List<User> users = readUsersFromJson();

        int indexToUpdate = -1;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(userId)) {
                indexToUpdate = i;
                break;
            }
        }

        if (indexToUpdate != -1) {
            User existingUser = users.get(indexToUpdate);

            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setLastName(updatedUser.getLastName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPassword(updatedUser.getPassword());

            writeUsersToJson(users);
            return ResponseEntity.ok(existingUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
