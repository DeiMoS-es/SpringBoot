package com.movie.rating.user.controllers;

import com.movie.rating.user.models.dtos.UserRequest;
import com.movie.rating.user.models.dtos.UserResponse;
import com.movie.rating.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest){
        try {
            this.userService.createUser(userRequest);
            URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("{userName}")
                    .buildAndExpand(userRequest.getUserName())
                    .toUri();
            return ResponseEntity.created(location).body(userRequest);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

}
