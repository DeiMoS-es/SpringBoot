package com.movie.rating.users.entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long userId;
    @NotBlank
    private String userName;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    private LocalDateTime createdAt;
    private String role;
}
