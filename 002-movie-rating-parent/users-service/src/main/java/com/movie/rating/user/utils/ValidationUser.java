package com.movie.rating.user.utils;

import com.movie.rating.user.models.dtos.UserRequest;

public class ValidationUser {
    public static void validateUser(UserRequest userRequest){
        validateUserName(userRequest.getUserName());
        validatePassword(userRequest.getPassword());
        validateEmail(userRequest.getEmail());
    }

    private static void validateUserName(String userName){
        if(userName == null || userName.isEmpty()){
            throw new IllegalArgumentException("User name is required");
        }
    }
    private static void validatePassword(String password){
        if(password == null || password.isEmpty()){
            throw new IllegalArgumentException("Password is required");
        }
    }
    private static void validateEmail(String email){
        if(email == null || email.isEmpty()){
            throw new IllegalArgumentException("Email is required");
        }
    }
}
