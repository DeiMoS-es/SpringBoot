package com.auth_service.service;

import com.auth_service.models.entity.User;
import com.auth_service.models.entity.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    RestTemplate restTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Cambia a UserResponse
        UserResponse userResponse = restTemplate.getForObject("http://localhost:8080/users/" + username, UserResponse.class);

        if (userResponse == null) {
            throw new UsernameNotFoundException("No user found with username: " + username);
        }

        // Convierte UserResponse a User de Spring Security
        return new User(
                userResponse.getUserName(),
                userResponse.getPassword(),
                userResponse.getEmail(),
                userResponse.getRole()
        );
    }
    /*
    @Autowired
    private RestTemplate restTemplate;

    public Optional<User> getUserByUserName(String userName) {
        try {
            User user = this.restTemplate.getForObject("http://localhost:8080/users/{userName}", User.class, userName);
            return Optional.ofNullable(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

     */
}
