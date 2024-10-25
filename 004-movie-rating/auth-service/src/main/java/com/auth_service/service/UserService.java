package com.auth_service.service;

import com.auth_service.models.entity.User;
import com.auth_service.models.entity.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserResponse userResponse = restTemplate.getForObject("http://localhost:8080/users/" + username, UserResponse.class);
            if (userResponse == null) {
                throw new UsernameNotFoundException("No user found with username: " + username);
            }
            return new User(
                    userResponse.getUserName(),
                    userResponse.getPassword(),
                    userResponse.getEmail(),
                    userResponse.getRole()
            );
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().value() == 404) {
                throw new UsernameNotFoundException("No user found with username: " + username, e);
            } else {
                throw new RuntimeException("Error in user service call", e);
            }
        }
    }
}
