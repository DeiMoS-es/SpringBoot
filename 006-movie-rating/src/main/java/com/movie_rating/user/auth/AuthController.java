package com.movie_rating.user.auth;

import com.movie_rating.user.model.dto.AuthResponse;
import com.movie_rating.user.model.dto.LoginRequest;
import com.movie_rating.user.model.dto.RegisterRequest;
import com.movie_rating.user.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "login")
    public Mono<ResponseEntity<AuthResponse>> login(@RequestBody LoginRequest request){
        return Mono.fromCallable(() -> {
            return ResponseEntity.ok(authService.login(request));
        });
    }

    @PostMapping(value = "register")
    public Mono<ResponseEntity<AuthResponse>> register(@RequestBody RegisterRequest request){
        return Mono.fromCallable(() -> {
            return ResponseEntity.ok(authService.register(request));
        });
    }
}
