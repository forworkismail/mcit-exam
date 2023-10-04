package com.example.demo.user;

import com.example.demo.user.requests.CreateUserRequest;
import com.example.demo.user.responses.UserResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> findAll();

    UserResponse findByUsername(String username);

    public UserResponse save(CreateUserRequest user);
}