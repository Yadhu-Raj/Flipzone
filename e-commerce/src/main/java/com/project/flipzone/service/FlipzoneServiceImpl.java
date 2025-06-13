package com.project.flipzone.service;

import com.project.flipzone.exception.customException.UserAlreadyExistsException;
import com.project.flipzone.flipzoneDTO.UserDTO;
import com.project.flipzone.flipzoneEntity.User;
import com.project.flipzone.flipzonerRepo.FlipRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class FlipzoneServiceImpl implements FlipzoneService {

    private final FlipRepo flipRepo;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public String addUser(UserDTO userDTO) throws Exception {
        if(flipRepo.existsByEmail(userDTO.getEmail())){
            throw new UserAlreadyExistsException("User already exists");
        }
        User user = new User();
        modelMapper.map(userDTO,user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        flipRepo.save(user);
        return "Successfully created user " + userDTO.getName();
    }
}
