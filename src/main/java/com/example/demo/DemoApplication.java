package com.example.demo;

import com.example.demo.letter.LetterService;
import com.example.demo.letter.enums.LetterType;
import com.example.demo.letter.requests.CreateLetterRequest;
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
    public CommandLineRunner demoData(UserService userService, LetterService letterService) {
        return args -> {
            // Create some sample users
            userService.save(new CreateUserRequest("admin", UserRole.ADMINISTRATORS.name(), "abc"));
            userService.save(new CreateUserRequest("worker", UserRole.DEPARTMENT_MEMBERS.name(), "abc"));
            userService.save(new CreateUserRequest("approver", UserRole.APPROVERS.name(), "abc"));

            // Start of correspondence chain:
            // A letter from an external entity to the admin regarding a project proposal
            Long letter1Id = letterService.createLetter(new CreateLetterRequest(LetterType.INCOMING.name(), "Project Proposal for 2024", 1L, 0L)).id();

            // The admin's response to the project proposal, requesting more details
            Long letter2Id = letterService.createLetter(new CreateLetterRequest(LetterType.OUTGOING.name(), "Response to Project Proposal: Need more details", 1L, letter1Id)).id();

            // A letter from the external entity with the details admin asked for
            Long letter3Id = letterService.createLetter(new CreateLetterRequest(LetterType.INCOMING.name(), "Additional Details for Project Proposal 2024", 1L, letter2Id)).id();

            // Worker's response acknowledging the details and suggesting a meeting
            Long letter4Id = letterService.createLetter(new CreateLetterRequest(LetterType.OUTGOING.name(), "Acknowledgment of Project Details and Suggesting Meeting", 2L, letter3Id)).id();

            // External entity's confirmation for the meeting
            Long letter5Id = letterService.createLetter(new CreateLetterRequest(LetterType.INCOMING.name(), "Meeting Confirmation for Project Proposal 2024", 1L, letter4Id)).id();

            // The approver's letter with some preparatory points for the meeting
            Long letter6Id = letterService.createLetter(new CreateLetterRequest(LetterType.OUTGOING.name(), "Preparatory Points for Upcoming Meeting", 3L, letter5Id)).id();

        };
    }

}