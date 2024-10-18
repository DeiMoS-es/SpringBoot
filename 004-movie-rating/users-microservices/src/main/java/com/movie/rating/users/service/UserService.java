package com.movie.rating.users.service;

import com.movie.rating.users.models.dtos.UserRequest;
import com.movie.rating.users.models.dtos.UserResponse;

import java.util.List;

public interface UserService {
    void createUser(UserRequest userRequest);
    List<UserResponse> getAllUsers();
    UserResponse getUserById(String userId);
    UserResponse getUserByUserName(String userName);
    void updateUser(UserRequest userRequest);
    void deleteUser(String userId);
}
