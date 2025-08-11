package com.project.flipzone.service;

import com.project.flipzone.flipzoneDTO.LoginResponse;
import com.project.flipzone.flipzoneDTO.RegisterRequest;
import com.project.flipzone.flipzoneDTO.LoginRequest;
import com.project.flipzone.flipzoneDTO.RegisterResponse;

public interface FlipzoneService {
    public RegisterResponse addUser(RegisterRequest registerRequest) throws Exception;

    public LoginResponse login(LoginRequest loginRequest) throws Exception;
}
