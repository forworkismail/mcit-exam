package com.example.demo.letter.requests;

public record CreateLetterRequest(String type, String content, Long userId, Long responseToId) {
}