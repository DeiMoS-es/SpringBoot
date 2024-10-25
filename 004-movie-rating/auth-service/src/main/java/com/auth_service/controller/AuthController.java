package com.auth_service.controller;

import com.auth_service.models.entity.dto.AuthResponse;
import com.auth_service.models.entity.dto.LoginRequest;
import com.auth_service.models.entity.dto.RegisterRequest;
import com.auth_service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            return ResponseEntity.ok(authService.login(loginRequest));
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(new AuthResponse(null, e.getResponseBodyAsString()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthResponse(null, e.getMessage()));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AuthResponse(null, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new AuthResponse(null, e.getMessage()));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest) {
        try{
            return ResponseEntity.ok(authService.register(registerRequest));
        }catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(new AuthResponse(null, e.getResponseBodyAsString()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new AuthResponse(null, e.getMessage()));
        }
    }
}
