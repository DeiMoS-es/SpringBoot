package com.auth_service.service;

import com.auth_service.models.entity.User;
import com.auth_service.models.entity.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
    @Autowired
    private UserDetailsService userDetailsService;

    // RabbitMQ
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Value("${rabbitmq.exchange}")
    private String exchange;
    @Value("${rabbitmq.routingkey}")
    private String routingkey;

    public AuthResponse login(LoginRequest loginRequest) {
        try {
            System.out.println("Intentando iniciar sesión para el usuario: " + loginRequest.getUserName());
            System.out.println("Autenticando la contraseña...: " + loginRequest.getPassword());

            // Cargar detalles del usuario usando UserDetailsService
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUserName());

            // Autenticar al usuario
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userDetails.getUsername(),
                            loginRequest.getPassword()
                    )
            );
            return AuthResponse.builder()
                    .token(jwtServices.getToken(userDetails)) // Generar el token JWT
                    .mensaje("Inicio de sesión exitoso")
                    .build();
        } catch (HttpClientErrorException e) {
            System.err.println("Error en la llamada al microservicio de usuarios: " + e.getResponseBodyAsString());
            e.printStackTrace();
            throw new RuntimeException("Error en la llamada al microservicio de usuarios", e);
        } catch (BadCredentialsException e) {
            System.err.println("Error de credenciales durante el inicio de sesión: " + e.getMessage());
            throw e;
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
            // Enviar evento de registro
            String registerEvent = "Usuario registrado: " + user.getUsername();
            sendMessage(registerEvent);
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

    private void sendMessage(String message){
        rabbitTemplate.convertAndSend(exchange, routingkey, message);
        System.out.println("Mensaje enviado: " + message);
    }
}
