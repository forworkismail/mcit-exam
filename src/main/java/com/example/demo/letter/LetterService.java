package com.example.demo.letter;

import com.example.demo.letter.requests.CreateLetterRequest;
import com.example.demo.letter.responses.LetterResponse;

import java.util.List;
import java.util.Optional;

public interface LetterService {
    LetterResponse createLetter(CreateLetterRequest request);

    Optional<LetterResponse> getLetterById(Long id);

    List<LetterResponse> getLettersByStatus(String status);

    void approveLetter(Long letterId);

    List<LetterResponse> getLettersByType(String type);
}