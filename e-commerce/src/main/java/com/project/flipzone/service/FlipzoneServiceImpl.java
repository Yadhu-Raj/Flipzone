package com.project.flipzone.service;

import com.project.flipzone.exception.customException.UserAlreadyExistsException;
import com.project.flipzone.flipzoneDTO.AddressDTO;
import com.project.flipzone.flipzoneEntity.Address;
import com.project.flipzone.flipzoneDTO.UserDTO;
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
    public String addUser(UserDTO userDTO) throws Exception {
        log.info("Adding user with email: {}", userDTO.getEmail());
        if(flipRepo.existsByEmail(userDTO.getEmail())){
            log.warn("User with email {} already exists", userDTO.getEmail());
            throw new UserAlreadyExistsException("User already exists");
        }
        User user = new User();
        modelMapper.map(userDTO,user);
        AddressDTO addressDTO = userDTO.getAddress();
        if (addressDTO != null) {
            Address address = new Address(
                    addressDTO.getStreet(),
                    addressDTO.getCity(),
                    addressDTO.getState(),
                    addressDTO.getPostalCode(),
                    addressDTO.getCountry()
            );
            user.setAddress(address);
        } else {
            log.warn("Address is null for user {}", userDTO.getEmail());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        flipRepo.save(user);

        log.info("User {} created successfully", userDTO.getName());
        return "Successfully created user " + userDTO.getName();
    }
}
