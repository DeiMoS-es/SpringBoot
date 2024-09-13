package com.movie.rating.user.services.impl;

import com.movie.rating.user.exceptions.UserException;
import com.movie.rating.user.models.dtos.UserRequest;
import com.movie.rating.user.models.dtos.UserResponse;
import com.movie.rating.user.models.entites.User;
import com.movie.rating.user.repositories.UserRepository;
import com.movie.rating.user.services.UserService;
import com.movie.rating.user.utils.ValidationUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void createUser(UserRequest userRequest) {
        try {
            Optional<User> existUser = userRepository.findByUserName(userRequest.getUserName());
            if(existUser.isEmpty()){
                ValidationUser.validateUser(userRequest);
                // TODO: Encriptar la contraseña
                User user = User.builder()
                        .userName(userRequest.getUserName())
                        .firstName(userRequest.getFirstName())
                        .lastName(userRequest.getLastName())
                        .email(userRequest.getEmail())
                        .password(userRequest.getPassword())
                        .build();
                userRepository.save(user);
            }else {
                throw new UserException("User with name: " + userRequest.getUserName() + " exists");
            }
        } catch (UserException e) {
            log.error("Error creating user: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::mapToUserResponse).toList();
    }

    @Override
    public UserResponse getUserById(String userId) {
        return null;
    }

    @Override
    public UserResponse getUserByUsername(String userName) {
        return null;
    }

    @Override
    public void updateUser(String userId, UserRequest userRequest) {

    }

    @Override
    public void deleteUser(String userId) {

    }

    private UserResponse mapToUserResponse(User user){
        return UserResponse.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }
}
