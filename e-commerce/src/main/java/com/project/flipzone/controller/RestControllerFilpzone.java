package com.project.flipzone.controller;

import com.project.flipzone.flipzoneDTO.UserDTO;
import com.project.flipzone.service.FlipzoneService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/users")
@RestController
//@RequiredArgsConstructor
public class RestControllerFilpzone {

    @Autowired
    private  FlipzoneService flipzoneService;

    @PostMapping("/adduser")
    public ResponseEntity<String> addUser (@Valid @RequestBody UserDTO user) throws Exception {
        String message = flipzoneService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }
}
