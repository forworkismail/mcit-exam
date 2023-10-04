package com.example.demo.user;

import com.example.demo.user.requests.CreateUserRequest;
import com.example.demo.user.responses.UserResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();

    Optional<UserResponse> findById(Long id);

    UserResponse save(CreateUserRequest userRequest);

    void deleteById(Long id);
}