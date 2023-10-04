package com.example.demo.user;

import com.example.demo.user.requests.CreateUserRequest;
import com.example.demo.user.responses.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<User> users = userService.findAll();
        List<UserResponse> result = users.stream()
                .map(user -> new UserResponse(user.getId(), user.getName(), user.getProfile().getBio()))
                .toList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserResponse>> getUserById(@PathVariable Long id) {
        Optional<UserResponse> userResponse = userService.findById(id);

        if (userResponse.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(userResponse);
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest request) {
        var response = userService.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}