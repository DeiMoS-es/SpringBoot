package com.movie_rating.user.service;

import com.movie_rating.user.model.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String getToken(UserDetails user);

    String getUsernameFromToken(String token);

    boolean isTokenValid(String token, UserDetails userDetails);
}
