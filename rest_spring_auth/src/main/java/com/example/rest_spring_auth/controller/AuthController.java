package com.example.rest_spring_auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest_spring_auth.service.UserService;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.rest_spring_auth.model.User;
import com.example.rest_spring_auth.security.JwtUtil;


@RestController
@RequestMapping("/auth")
public class AuthController {
    
    private final UserService userService;

    public AuthController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> request) {
        User user = userService.RegistrarUser(request.get("username"), request.get("password"));

        return ResponseEntity.ok(user);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        Optional<User> user = userService.buscarPorUsername(request.get("username"));
        if(user.isPresent() && user.get().getPassword().equals(request.get("password"))){
            String token = JwtUtil.generateToken(user.get().getUsername());
            return ResponseEntity.ok(Map.of("token", token));
        }   
        return ResponseEntity.status(401).body("Credenciais inválidas");
    }
    
}
