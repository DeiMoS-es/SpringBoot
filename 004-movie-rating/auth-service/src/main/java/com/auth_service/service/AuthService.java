package com.auth_service.service;

import com.auth_service.models.entity.User;
import com.auth_service.models.entity.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
// Esta se usa en la nueva versión
@Service
@RequiredArgsConstructor
public class AuthService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private JwtService jwtServices;
    @Autowired
    private AuthenticationManager  authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthResponse login(LoginRequest loginRequest) {
        try {
            System.out.println("Intentando iniciar sesión para el usuario: " + loginRequest.getUserName());
            System.out.println("Autenticando la contraseña...: " + loginRequest.getPassword());
            // Obtener detalles del usuario desde el microservicio de usuarios
            UserResponse userResponse = restTemplate.getForObject("http://localhost:8080/users/" + loginRequest.getUserName(), UserResponse.class);
            System.out.println("Detalles del usuario obtenidos: " + userResponse);

            // Verificar que el usuario exista y que la contraseña sea correcta
            if (userResponse != null && passwordEncoder.matches(loginRequest.getPassword(), userResponse.getPassword())) {
                System.out.println("Autenticación exitosa para el usuario: " + loginRequest.getUserName());

                // Convertir UserResponse a UserDetails (en este caso, a nuestra clase User que implementa UserDetails)
                User userDetails = convertToUserDetails(userResponse);

                // Aquí puedes construir y devolver la respuesta de autenticación
                return AuthResponse.builder()
                        .token(jwtServices.getToken(userDetails)) // Generar el token JWT
                        .mensaje("Inicio de sesión exitoso")
                        .build();
            } else {
                throw new BadCredentialsException("Invalid username or password.");
            }
        } catch (HttpClientErrorException e) {
            System.err.println("Error en la llamada al microservicio de usuarios: " + e.getResponseBodyAsString());
            e.printStackTrace();
            throw new RuntimeException("Error en la llamada al microservicio de usuarios", e);
        } catch (BadCredentialsException e) {
            System.err.println("Error durante el inicio de sesión: " + e.getMessage());
            throw e; // Re-throw the exception to be handled in the controller
        }catch (Exception e) {
            System.err.println("Error durante el inicio de sesión: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error durante el inicio de sesión", e);
        }
    }

    private User convertToUserDetails(UserResponse userResponse) {
        return User.builder()
                .userName(userResponse.getUserName())
                .password(userResponse.getPassword()) // Contraseña encriptada
                .email(userResponse.getEmail())
                .role(mapRole(userResponse.getRole())) // Convertir roles de 0 y 1 a "USER" o "ADMIN"
                .build();
    }

    private String mapRole(String roleFromResponse) {
        // Convierte los valores de 0 y 1 en los roles correspondientes
        return roleFromResponse.equals("1") ? "ADMIN" : "USER";
    }

    public AuthResponse register(RegisterRequest registerRequest) {
        User user = User.builder()
                .userName(registerRequest.getUserName())
                .password(registerRequest.getPassword())
                .email(registerRequest.getEmail())
                .role("USER")
                .build();
        // Se tiene que mapear a userRequest, ya que es lo que espera recibir el mciroservicio de usuarios
        UserRequest userRequest = UserRequest.builder()
                .userName(registerRequest.getUserName())
                .email(registerRequest.getEmail())
                .password(registerRequest.getPassword())
                .build();
        try {
            // Envio el usuario al microservicio de usuarios
            restTemplate.postForObject("http://localhost:8080/users", userRequest, UserRequest.class);
        } catch (HttpClientErrorException e) {
            // Manejo de errores específicos del cliente HTTP
            throw new RuntimeException("Error al registrar el usuario: " + e.getResponseBodyAsString(), e);
        } catch (Exception e) {
            // Manejo de otros errores
            throw new RuntimeException("Error inesperado al registrar el usuario", e);
        }
        return AuthResponse.builder()
                .token(jwtServices.getToken(user))
                .mensaje("Usuario registrado correctamente")
                .build();
    }
}
