package com.movie.rating.user.services.impl;

import com.movie.rating.user.models.dtos.AuthResponse;
import com.movie.rating.user.models.dtos.LoginRequest;
import com.movie.rating.user.models.dtos.RegisterRequest;
import com.movie.rating.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;

    public AuthResponse register(RegisterRequest request) {
        userService.registerUser(request);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }

    public AuthResponse login(LoginRequest loginRequest) {
        return null;
    }
}
