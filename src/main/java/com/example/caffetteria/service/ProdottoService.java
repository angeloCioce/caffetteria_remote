package com.example.caffetteria.service;

import com.example.caffetteria.model.Cliente;
import com.example.caffetteria.model.Ordine;
import com.example.caffetteria.model.Prodotto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProdottoService {

	 	List <Prodotto> findAll();

	    Prodotto save(Prodotto prodotto);
	     
	    Optional <Prodotto> findById(Long id);

	    void delete(Long id);
	    
	    Ordine update(Long id, String nome_prodotto, String descrizione, Double prezzo_ingrosso, Double prezzo_dettaglio, Integer quantita, String tipologia);
}
