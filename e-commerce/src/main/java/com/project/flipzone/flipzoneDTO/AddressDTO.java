package com.project.flipzone.flipzoneDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    @NotBlank(message = "street name is required")
    private String street;

    @NotBlank(message = "city is required")
    private String city;

    @NotBlank(message = "postal code is required")
    private String postalCode;

    @NotBlank(message = "state is required")
    private String state;

    @NotBlank(message = "country is required")
    private String country;
}
