package com.example.demo.student.requests;

import java.util.Set;

public record CreateStudentRequest(String name, Set<Long> courseIds) {
}