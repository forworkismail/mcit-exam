package com.example.demo.profile;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;

    @Override
    public List<Profile> findAll() {
        return profileRepository.findAll();
    }

    @Override
    public Optional<Profile> findById(Long id) {
        return profileRepository.findById(id);
    }

    @Override
    public Profile save(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    public void deleteById(Long id) {
        profileRepository.deleteById(id);
    }
}