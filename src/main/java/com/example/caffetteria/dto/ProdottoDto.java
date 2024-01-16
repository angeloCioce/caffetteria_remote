package com.example.caffetteria.dto;

import lombok.Data;

@Data
public class ProdottoDto {

    private Long id;
    private String nome_prodotto;
    private String descrizione;
    private Double prezzo_ingrosso;
    private Double prezzo_dettaglio;
    private Integer quantita;
    private String tipologia;
}
