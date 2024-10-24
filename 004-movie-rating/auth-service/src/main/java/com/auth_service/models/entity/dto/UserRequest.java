package com.auth_service.models.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  Se necetiará para la creación de un usuario ya que el microservicio de usuario devuelve un objeto de este tipo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    private String userName;
    private String email;
    private String password;
}
