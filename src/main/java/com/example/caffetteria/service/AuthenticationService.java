package com.example.caffetteria.service;

import com.example.caffetteria.dto.AuthenticationRequest;
import com.example.caffetteria.dto.AuthenticationResponse;
import com.example.caffetteria.dto.RegisterRequest;
import com.example.caffetteria.model.Utente;
import com.example.caffetteria.repository.UtenteRepository;
import com.example.caffetteria.userRole.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    @Autowired
    private final UtenteRepository repository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest req) {
        var utente = Utente
                .builder()
                .username(req.getUsername())
                .password(passwordEncoder.encode(req.getPassword()))
                .ruolo(UserRole.DIPENDENTE)
                .build();
        repository.save(utente);
        var jwtToken = jwtService.generateToken(utente);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest req) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getUsername(),
                        req.getPassword()
                )
        );
        var user = repository.findByUsername(req.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }
}
