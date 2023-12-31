package com.example.caffetteria.service;

import com.example.caffetteria.model.Cliente;
import com.example.caffetteria.model.Ordine;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrdineService {

	 	List <Ordine> findAll();

	    Ordine save(Ordine ordine);
	     
	    Optional <Ordine> findById(Long id);

	    void delete(Long id);
	    
	    Ordine update(Long id, LocalDateTime data_ordine, Double prezzo_totale);
}
