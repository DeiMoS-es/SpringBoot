package com.movie_rating.user.service;

import com.movie_rating.user.model.entity.User;

public interface JwtService {
    String getToken(User user);
}
