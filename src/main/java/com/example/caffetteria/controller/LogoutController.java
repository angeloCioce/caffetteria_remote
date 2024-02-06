package com.example.caffetteria.controller;

import com.example.caffetteria.config.TokenExtractor;
import com.example.caffetteria.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class LogoutController {

    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final TokenExtractor tokenExtractor;

    public LogoutController(JwtService jwtService, TokenExtractor tokenExtractor) {
        this.jwtService = jwtService;
        this.tokenExtractor = tokenExtractor;
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        String token = tokenExtractor.extractToken(request);

        if (token != null) {
            jwtService.addToBlacklist(token);
        }
        return ResponseEntity.ok("Logout effettuato con successo");
    }
}

