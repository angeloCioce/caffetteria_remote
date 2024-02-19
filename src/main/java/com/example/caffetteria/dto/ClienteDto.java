package com.example.caffetteria.dto;

import lombok.Data;

@Data
public class ClienteDto {

    private Long id;
    private String nome;
    private String cognome;
    private int page;
    private int size;
}
