package com.example.rest_spring_auth.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.rest_spring_auth.model.User;
import com.example.rest_spring_auth.repository.UserRepository;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public UsuarioDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User usuario = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuário não encontrado"));

        return org.springframework.security.core.userdetails.User
                .withUsername(usuario.getUsername())
                .password(usuario.getPassword())
                .roles("USER")
                .build();
    }
}
