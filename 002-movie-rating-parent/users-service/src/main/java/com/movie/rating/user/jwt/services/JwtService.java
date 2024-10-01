package com.movie.rating.user.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String getToken(UserDetails userDetails);

    String getUserNameFromToken(String token);

    boolean isTokenValid(String token, UserDetails userDetails);
}
