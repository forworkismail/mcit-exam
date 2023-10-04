package com.example.demo.letter.responses;

public record LetterResponse(Long id, String uniqueIdentifier, String type, String content, Long userId, String date,
                             String status, Long responseToId) {
}