package com.example.demo.user;

import com.example.demo.profile.Profile;
import com.example.demo.profile.ProfileRepository;
import com.example.demo.user.requests.CreateUserRequest;
import com.example.demo.user.responses.UserResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ProfileRepository profileRepository;

    @Override
    public List<User> findAll() {

        return userRepository.findAll();
    }

    @Override
    public Optional<UserResponse> findById(Long id) {
        return userRepository.findById(id)
                .map(user -> new UserResponse(user.getId(), user.getName(), user.getProfile().getBio()));
    }

    @Override
    @Transactional
    public UserResponse save(CreateUserRequest userRequest) {
        var user = new User();
        user.setName(userRequest.name());
        var savedUser = userRepository.save(user);

        var profile = new Profile();
        profile.setUser(user);
        profile.setBio(userRequest.bio());
        var savedProfile = profileRepository.save(profile);

        return new UserResponse(savedUser.getId(), savedUser.getName(), savedProfile.getBio());
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}