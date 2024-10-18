package com.movie.rating.users.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;
    @NotBlank
    @Column(unique = true, nullable = false)
    private String userName;
    @NotBlank
    @Column(unique = true, nullable = false)
    private String email;
    @NotBlank
    private String password;
    private LocalDateTime createdAt;
    private Role role;
}
