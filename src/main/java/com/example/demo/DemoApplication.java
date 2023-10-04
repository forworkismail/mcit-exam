package com.example.demo;

import com.example.demo.user.UserService;
import com.example.demo.user.enums.UserRole;
import com.example.demo.user.requests.CreateUserRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner demoData(UserService userService) {
        return args -> {
            // Create some sample users
            userService.save(new CreateUserRequest("admin", UserRole.ADMINISTRATORS.name(), "abc"));
            userService.save(new CreateUserRequest("worker", UserRole.DEPARTMENT_MEMBERS.name(), "abc"));
            userService.save(new CreateUserRequest("approver", UserRole.APPROVERS.name(), "abc"));
        };
    }

}