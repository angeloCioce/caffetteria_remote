package com.example.caffetteria.service;

import com.example.caffetteria.model.Cliente;
import com.example.caffetteria.model.Ordine;
import com.example.caffetteria.model.Prodotto;
import com.example.caffetteria.model.Utente;

import java.util.List;
import java.util.Optional;

public interface UtenteService {

	 	List <Utente> findAll();

	    Utente save(Utente utente);

		Utente findById(Long id);

	    void delete(Long id);

		Utente update(Long id, Utente utenteRequest);
}
