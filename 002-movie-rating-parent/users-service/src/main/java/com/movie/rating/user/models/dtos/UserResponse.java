package com.movie.rating.user.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private UUID userId;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
