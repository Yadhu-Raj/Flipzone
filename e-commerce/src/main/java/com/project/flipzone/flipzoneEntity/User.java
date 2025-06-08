package com.project.flipzone.flipzoneEntity;


import com.project.flipzone.flipzoneDTO.AddressDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String Password;

    @Embedded
    private Address address;

    @Column(nullable = false)
    private String mobileNo;
}
