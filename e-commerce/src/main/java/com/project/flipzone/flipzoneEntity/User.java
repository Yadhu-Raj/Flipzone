package com.project.flipzone.flipzoneEntity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "user_id")
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
