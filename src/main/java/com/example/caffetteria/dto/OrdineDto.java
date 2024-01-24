package com.example.caffetteria.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrdineDto {

    private Long id;
    private LocalDateTime data_ordine;
    private Double prezzo_totale;
    private Long id_cliente;
    private Long id_utente;
    private String username_utente;
    private String nome_cliente;
    private String cognome_cliente;
    private List<ProdottoDto> prodotti;
}
