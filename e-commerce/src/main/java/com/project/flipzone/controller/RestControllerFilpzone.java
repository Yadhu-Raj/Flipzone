package com.project.flipzone.controller;

import com.project.flipzone.flipzoneDTO.UserDTO;
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
    public ResponseEntity<String> addUser ( @Valid @RequestBody UserDTO user) throws Exception {
        log.info("Received request to add user: {}", user.getEmail());
        try {
            String message = flipzoneService.addUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(message);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/test")
    public String test() {
        return "Hello, Flipzone!";
    }
}
