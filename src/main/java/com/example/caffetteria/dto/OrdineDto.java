package com.example.caffetteria.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrdineDto {

    private Long id;
    private LocalDateTime data_ordine;
    private Double prezzo_totale;
    private Long id_cliente;
    private Long id_utente;
    private Long id_prodotto_ordine;
    private Integer quantita_totale;
}
