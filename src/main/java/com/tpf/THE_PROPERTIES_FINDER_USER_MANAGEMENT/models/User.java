package com.tpf.THE_PROPERTIES_FINDER_USER_MANAGEMENT.models;
import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class User {
    @Nullable
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
