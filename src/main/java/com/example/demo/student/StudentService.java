package com.example.demo.student;

import com.example.demo.student.requests.CreateStudentRequest;
import com.example.demo.student.responses.StudentResponse;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<StudentResponse> findAll();

    Optional<StudentResponse> findById(Long id);

    StudentResponse save(CreateStudentRequest request);

    void deleteById(Long id);
}