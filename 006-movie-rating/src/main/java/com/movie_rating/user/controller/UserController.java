package com.movie_rating.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, User! ruta protegida";
    }

    @GetMapping("/hello2")
    public Mono<String> hello2() {
        return Mono.just("Hello2, User! ruta protegida");
    }
}
