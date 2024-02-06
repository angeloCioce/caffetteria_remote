package com.example.caffetteria.config;

import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TokenBlacklist {

    private final Set<String> blacklist = ConcurrentHashMap.newKeySet();

    public void addToBlacklist(String token) {
        blacklist.add(token);
    }

    public boolean isTokenBlacklisted(String token) {
        return blacklist.contains(token);
    }
}
