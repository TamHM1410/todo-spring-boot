package com.example.petprojectspringboot.user;

import com.google.gson.annotations.Expose;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="user_model")
public class UserEntity {
    @Expose
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;
}
