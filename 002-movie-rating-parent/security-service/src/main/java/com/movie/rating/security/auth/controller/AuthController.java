package com.movie.rating.security.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(){
        return "Login from public endpoint";
    }
    @PostMapping("/register")
    public String register(){
        return "Register from public endpoint";
    }
}
