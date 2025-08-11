package com.project.flipzone.service;

import com.project.flipzone.exception.customException.InvalidCredentialsException;
import com.project.flipzone.exception.customException.ResourceNotFoundException;
import com.project.flipzone.exception.customException.UserAlreadyExistsException;
import com.project.flipzone.flipzoneDTO.LoginRequest;
import com.project.flipzone.flipzoneDTO.LoginResponse;
import com.project.flipzone.flipzoneDTO.RegisterRequest;
import com.project.flipzone.flipzoneDTO.RegisterResponse;
import com.project.flipzone.flipzoneEntity.User;
import com.project.flipzone.flipzonerRepo.FlipRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class FlipzoneServiceImpl implements FlipzoneService {

    private final FlipRepo flipRepo;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public RegisterResponse addUser(RegisterRequest registerRequest) throws UserAlreadyExistsException {
        log.info("Adding user with email: {}", registerRequest.getEmail());
        if(flipRepo.existsByEmail(registerRequest.getEmail())){
            log.warn("User with email {} already exists", registerRequest.getEmail());
            throw new UserAlreadyExistsException("User already exists");
        }
        User user = new User();
        modelMapper.map(registerRequest,user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("user details {}", user);
        user = flipRepo.save(user);

        log.info("User {} created successfully", registerRequest.getName());
        return new RegisterResponse(
                user.getName(),
                user.getEmail(),
                "User registered successfully"
        );
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) throws InvalidCredentialsException {
        log.info("User login attempt for email: {}", loginRequest.getEmail());
        User user = flipRepo.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + loginRequest.getEmail()));
        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            log.info("User {} logged in successfully", user.getEmail());
            return new LoginResponse(user.getName());
        } else {
            log.warn("Invalid password for user {}", loginRequest.getEmail());
            throw new InvalidCredentialsException("Invalid password");
        }
    }
}
