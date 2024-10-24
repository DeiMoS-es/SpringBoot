package com.movie.rating.users.service.impl;

import com.movie.rating.users.exception.UserException;
import com.movie.rating.users.models.dtos.UserRequest;
import com.movie.rating.users.models.dtos.UserResponse;
import com.movie.rating.users.models.entity.Role;
import com.movie.rating.users.models.entity.User;
import com.movie.rating.users.repository.UserRepository;
import com.movie.rating.users.service.UserService;
import com.movie.rating.users.utils.ValidationUser;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void createUser(UserRequest userRequest) {
        try {
            Optional<User> existingUser = userRepository.findByUserName(userRequest.getUserName());
            Optional<User> existingEmail = userRepository.findByEmail(userRequest.getEmail());
            if(existingUser.isEmpty() && existingEmail.isEmpty()){
                ValidationUser.validateUser(userRequest);
                String hashedPassword = BCrypt.hashpw(userRequest.getPassword(), BCrypt.gensalt());
                User user = User.builder()
                        .userName(userRequest.getUserName())
                        .password(hashedPassword)
                        .email(userRequest.getEmail())
                        .createdAt(LocalDateTime.now())
                        .role( Role.USER)
                        .build();
                userRepository.save(user);
            }else {
                if (existingUser.isPresent()) {
                    throw new UserException("User with name: " + userRequest.getUserName() + " exists");
                } else if (existingEmail.isPresent()) {
                    throw new UserException("Email: " + userRequest.getEmail() +  "  exists");
                }
            }
        } catch (UserException e) {
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
    public UserResponse getUserByUserName(String userName) {
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() ->  new UserException("User not found"));
        return mapToUserResponse(user);
    }


    @Override
    public void updateUser(UserRequest userRequest) {

    }

    @Override
    public void deleteUser(String userId) {

    }

    /**
     * Función que mapea un objeto User a un objeto UserResponse
     */
    private UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .role(user.getRole())
                .password(user.getPassword())
                .createdAt(user.getCreatedAt().toString())
                .build();
    }
}
