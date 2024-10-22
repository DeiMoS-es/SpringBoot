package com.movie.rating.users.controller;

import com.movie.rating.users.models.dtos.UserRequest;
import com.movie.rating.users.models.dtos.UserResponse;
import com.movie.rating.users.service.UserService;
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

    @PostMapping
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

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @GetMapping("/{userName}")
    public ResponseEntity<UserResponse> getUserByUserName(@PathVariable String userName){
        System.out.println("User name: " + userName);
        UserResponse userResponse = this.userService.getUserByUserName(userName);
        System.out.println("User response: " + userResponse);
        return ResponseEntity.ok(userResponse);
    }
}
