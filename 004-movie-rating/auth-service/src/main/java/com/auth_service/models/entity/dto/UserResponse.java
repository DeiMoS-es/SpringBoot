package com.auth_service.models.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Clase que representa la respuesta de un usuario
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private String userId;
    private String userName;
    private String email;
    private String role;
    private String password;
    private String createdAt;
}
