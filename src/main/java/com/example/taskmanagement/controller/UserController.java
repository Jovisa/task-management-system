package com.example.taskmanagement.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.taskmanagement.dto.JwtResponse;
import com.example.taskmanagement.dto.RegistrationRequest;
import com.example.taskmanagement.entity.AppUser;
import com.example.taskmanagement.security.JwtService;
import com.example.taskmanagement.service.RegistrationService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final RegistrationService registrationService;
    private final JwtService jwtService;

    @Operation(summary = "Register new user", description = "Public endpoint — no authentication required")
    @PostMapping("/accounts")
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationRequest request) {
        AppUser user = registrationService.register(request);
        return ResponseEntity.ok(user); //todo refactor -> return dto, or just status
    }

    @Operation(description = "Basic Auth required to generate Javascript Web Token")
    @PostMapping("/auth/token")
    public ResponseEntity<JwtResponse> getToken(Authentication authentication) {
        JwtResponse jwtResponse = jwtService.getToken(authentication);
        return ResponseEntity.ok(jwtResponse);
    }

}
