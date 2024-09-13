package com.movie.rating.user.services;
import com.movie.rating.user.models.dtos.UserRequest;
import com.movie.rating.user.models.dtos.UserResponse;
import java.util.List;
import java.util.UUID;

public interface UserService {
    void createUser(UserRequest userRequest);
    List<UserResponse> getAllUsers();
    UserResponse getUserById(String userId);
    UserResponse getUserByUsername(String userName);
    void updateUser(String userId, UserRequest userRequest);
    void deleteUser(String userId);
}
