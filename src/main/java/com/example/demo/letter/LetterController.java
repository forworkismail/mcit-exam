package com.example.demo.letter;

import com.example.demo.letter.requests.CreateLetterRequest;
import com.example.demo.letter.responses.LetterResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/letters")
public class LetterController {

    private final LetterService letterService;

    public LetterController(LetterService letterService) {
        this.letterService = letterService;
    }

    @PostMapping
    public ResponseEntity<LetterResponse> createLetter(@RequestBody CreateLetterRequest request) {
        LetterResponse response = letterService.createLetter(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LetterResponse> getLetterById(@PathVariable Long id) {
        Optional<LetterResponse> response = letterService.getLetterById(id);
        return response.map(letterResponse -> new ResponseEntity<>(letterResponse, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<LetterResponse>> getLettersByStatus(@PathVariable String status) {
        List<LetterResponse> responses = letterService.getLettersByStatus(status);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @PatchMapping("/{id}/approve")
    public ResponseEntity<Void> approveLetter(@PathVariable Long id) {
        try {
            letterService.approveLetter(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalStateException | IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/type/{type}")
    public List<LetterResponse> getLettersByType(@PathVariable String type) {
        return letterService.getLettersByType(type);
    }

    @GetMapping("/{id}/response-chain")
    public ResponseEntity<List<Letter>> getResponseChain(@PathVariable Long id) {
        return ResponseEntity.ok(letterService.getResponseChain(id));
    }
}