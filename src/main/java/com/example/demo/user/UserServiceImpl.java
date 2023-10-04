package com.example.demo.user;

import com.example.demo.department.DepartmentRepository;
import com.example.demo.user.enums.UserRole;
import com.example.demo.user.requests.CreateUserRequest;
import com.example.demo.user.responses.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;

    public UserServiceImpl(UserRepository userRepository, DepartmentRepository departmentRepository) {
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<UserResponse> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserResponse(user.getId(), user.getUsername(), user.getRole().toString()))
                .toList();
    }

    @Override
    public UserResponse findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return new UserResponse(user.getId(), user.getUsername(), user.getRole().toString());
        }
        return null;
    }

    @Override
    public UserResponse save(CreateUserRequest user) {
        var newUser = new User();
        newUser.setUsername(user.username());
        newUser.setPassword(user.password());
        newUser.setRole(UserRole.valueOf(user.role()));
        User savedUser = userRepository.save(newUser);
        return new UserResponse(savedUser.getId(), savedUser.getUsername(), savedUser.getRole().toString());
    }

}