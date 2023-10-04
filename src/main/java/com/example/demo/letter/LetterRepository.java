package com.example.demo.letter;

import com.example.demo.letter.enums.LetterStatus;
import com.example.demo.letter.enums.LetterType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LetterRepository extends JpaRepository<Letter, Long> {
    List<Letter> findByStatus(LetterStatus status);

    List<Letter> findByType(LetterType type);
}