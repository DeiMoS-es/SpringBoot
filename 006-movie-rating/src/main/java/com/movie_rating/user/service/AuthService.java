package com.movie_rating.user.service;

import com.movie_rating.user.model.dto.AuthResponse;
import com.movie_rating.user.model.dto.LoginRequest;
import com.movie_rating.user.model.dto.RegisterRequest;

public interface AuthService {
    AuthResponse login(LoginRequest request);
    AuthResponse register(RegisterRequest request);
}
