package com.project.flipzone.controller;

import com.project.flipzone.flipzoneDTO.LoginResponse;
import com.project.flipzone.flipzoneDTO.RegisterRequest;
import com.project.flipzone.flipzoneDTO.LoginRequest;
import com.project.flipzone.flipzoneDTO.RegisterResponse;
import com.project.flipzone.service.FlipzoneService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/api/v1/users")
@RestController
@RequiredArgsConstructor
public class RestControllerFilpzone {

    private final FlipzoneService flipzoneService;

    @PostMapping("/adduser")
    public ResponseEntity<RegisterResponse> addUser(@Valid @RequestBody RegisterRequest user) throws Exception {
        log.info("Received request to add user: {}", user.getEmail());
        RegisterResponse response = flipzoneService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
        log.info("Received login request for user: {}", loginRequest.getEmail());
        LoginResponse response = flipzoneService.login(loginRequest);
            return ResponseEntity.ok(response);
    }
}
