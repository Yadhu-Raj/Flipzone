package com.project.flipzone.flipzoneDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    @NotBlank(message = "username is required")
    private String name;

    @NotNull(message = "age is required")
    private Integer age;

    @NotBlank(message = "email is required")
    @Email(message = "please provide a valid email")
    private String email;

    @NotBlank(message = "password is required")
    @Size(min = 8, message = "password should be longer than 8 characters")
    private String password;

    @Valid
    private AddressDTO address;

    @NotBlank(message = "mobile number is required")
    private String mobileNo;

}
