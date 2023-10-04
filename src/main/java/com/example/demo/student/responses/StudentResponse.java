package com.example.demo.student.responses;

import com.example.demo.course.responses.CourseResponse;

import java.util.Set;

public record StudentResponse(Long id,
                              String name,
                              Set<CourseResponse> courses) {
}