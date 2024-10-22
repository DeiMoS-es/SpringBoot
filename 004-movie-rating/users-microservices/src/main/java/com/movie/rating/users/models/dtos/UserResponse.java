package com.movie.rating.users.models.dtos;

import com.movie.rating.users.models.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private String userId;
    private String userName;
    private String email;
    private Role role;
    private String password;
    private String createdAt;
}
