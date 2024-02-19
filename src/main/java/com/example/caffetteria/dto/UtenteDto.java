package com.example.caffetteria.dto;

import lombok.Data;

@Data
public class UtenteDto {

    private Long id;
    private String username;
    private String password;
    private String ruolo;
    private int page;
    private int size;
}
