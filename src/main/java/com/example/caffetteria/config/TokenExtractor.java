package com.example.caffetteria.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


@Component
public class TokenExtractor {

    public String extractToken(HttpServletRequest request) {
        // Ottieni il token JWT dall'header "Authorization"
        String authHeader = request.getHeader("Authorization");
        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }

        // Implementa altre logiche per estrarre il token da cookie, corpo della richiesta, ecc. se necessario

        return null; // Se il token non è stato trovato
    }
}

