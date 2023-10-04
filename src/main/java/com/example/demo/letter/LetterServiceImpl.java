package com.example.demo.letter;

import com.example.demo.letter.enums.LetterStatus;
import com.example.demo.letter.enums.LetterType;
import com.example.demo.letter.requests.CreateLetterRequest;
import com.example.demo.letter.responses.LetterResponse;
import com.example.demo.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class LetterServiceImpl implements LetterService {

    private final LetterRepository letterRepository;
    private final UserRepository userRepository;

    @Override
    public LetterResponse createLetter(CreateLetterRequest request) {
        var letter = new Letter();
        String uniqueIdentifier = System.currentTimeMillis() + "-" + UUID.randomUUID().toString();
        letter.setUniqueIdentifier(uniqueIdentifier);
        letter.setType(LetterType.valueOf(request.type()));
        letter.setContent(request.content());
        letter.setCreatedBy(userRepository.findById(request.userId()).orElseThrow());
        letter.setDate(new Date());
        letter.setStatus(LetterStatus.DRAFT);
        letter.setResponseTo(letterRepository.findById(request.responseToId()).orElse(null));
        var savedLetter = letterRepository.save(letter);
        return new LetterResponse(savedLetter.getId(), savedLetter.getUniqueIdentifier(),
                savedLetter.getType().toString(), savedLetter.getContent(),
                savedLetter.getCreatedBy().getId(), savedLetter.getDate().toString(),
                savedLetter.getStatus().toString(),
                savedLetter.getResponseTo() != null ? savedLetter.getResponseTo().getId() : null);

    }

    @Override
    public Optional<LetterResponse> getLetterById(Long id) {
        Optional<Letter> letter = letterRepository.findById(id);
        return letter.map(l -> new LetterResponse(l.getId(), l.getUniqueIdentifier(),
                l.getType().toString(), l.getContent(),
                l.getCreatedBy().getId(), l.getDate().toString(),
                l.getStatus().toString(),
                l.getResponseTo() != null ? l.getResponseTo().getId() : null));
    }

    @Override
    public List<LetterResponse> getLettersByStatus(String status) {
        List<Letter> letters = letterRepository.findByStatus(LetterStatus.valueOf(status));

        return letters.stream()
                .map(l -> new LetterResponse(
                        l.getId(),
                        l.getUniqueIdentifier(),
                        l.getType().toString(),
                        l.getContent(),
                        l.getCreatedBy().getId(),
                        l.getDate().toString(),
                        l.getStatus().toString(),
                        l.getResponseTo() != null ? l.getResponseTo().getId() : null))
                .collect(Collectors.toList());
    }

    @Override
    public void approveLetter(Long letterId) {
        Optional<Letter> optionalLetter = letterRepository.findById(letterId);

        if (optionalLetter.isPresent()) {
            Letter letter = optionalLetter.get();

            if (letter.getStatus() == LetterStatus.DRAFT) {
                letter.setStatus(LetterStatus.APPROVED);
                letterRepository.save(letter);
            } else {
                // Handle the scenario where the letter is already approved or in some other state
                throw new IllegalStateException("Letter is not in DRAFT status");
            }
        } else {
            throw new IllegalArgumentException("Letter with id " + letterId + " not found");
        }
    }

    @Override
    public List<LetterResponse> getLettersByType(String type) {
        List<Letter> letters = letterRepository.findByType(LetterType.valueOf(type));

        return letters.stream()
                .filter(l -> l.getStatus() == LetterStatus.APPROVED)
                .map(l -> new LetterResponse(
                        l.getId(),
                        l.getUniqueIdentifier(),
                        l.getType().toString(),
                        l.getContent(),
                        l.getCreatedBy().getId(),
                        l.getDate().toString(),
                        l.getStatus().toString(),
                        l.getResponseTo() != null ? l.getResponseTo().getId() : null))
                .collect(Collectors.toList());
    }

    @Override
    public List<Letter> getResponseChain(Long id) {
        List<Letter> responseChain = new ArrayList<>();
        Optional<Letter> currentLetter = letterRepository.findById(id);

        while (currentLetter.isPresent() && currentLetter.get().getId() != null) {
            responseChain.add(currentLetter.get());
            currentLetter = letterRepository.findById(currentLetter.get().getId());
        }

        return responseChain;
    }

}