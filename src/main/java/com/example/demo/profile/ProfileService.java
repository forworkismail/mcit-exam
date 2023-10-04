package com.example.demo.profile;

import java.util.List;
import java.util.Optional;

public interface ProfileService {
    List<Profile> findAll();

    Optional<Profile> findById(Long id);

    Profile save(Profile profile);

    void deleteById(Long id);
}