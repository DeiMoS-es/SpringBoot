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

}
