package com.movie.rating.users.utils;

import com.movie.rating.users.models.dtos.UserRequest;

public class ValidationUser {

    public static  void validateUser(UserRequest userRequest){
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
        String passwordRegex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        if (!password.matches(passwordRegex)) {
            throw new IllegalArgumentException("La contraseña debe tener al menos una mayúscula, un número, un símbolo y un mínimo de 8 caracteres");
        }
    }
    private static void validateEmail(String email){
        if(email == null || email.isEmpty()){
            throw new IllegalArgumentException("Email is required");
        }
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!email.matches(emailRegex)) {
            throw new IllegalArgumentException("Correo electrónico no válido");
        }
    }
}
