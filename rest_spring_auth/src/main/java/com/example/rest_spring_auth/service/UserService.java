package com.example.rest_spring_auth.service;

import org.springframework.stereotype.Service;

import com.example.rest_spring_auth.repository.UserRepository;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.rest_spring_auth.model.User;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User RegistrarUser(String username, String password){
        String senhaCriptografada = passwordEncoder.encode(password);
        User user = new User(username, senhaCriptografada);
        return userRepository.save(user);
    }

    public Optional<User> buscarPorUsername(String username){
        return userRepository.findByUsername(username);
    }

}