package com.example.taskmanagement.service;

import com.example.taskmanagement.dto.RegistrationRequest;
import com.example.taskmanagement.entity.AppUser;
import com.example.taskmanagement.exception.EmailNotUniqueException;
import com.example.taskmanagement.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUser register(RegistrationRequest request) {
        if (userRepository.existsByEmailIgnoreCase(request.email())) {
            throw new EmailNotUniqueException("Email already taken");
        }

        AppUser user = new AppUser();
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));

        return userRepository.save(user);
    }

}
