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
	     
	    Optional <Utente> findById(Long id);

	    void delete(Long id);
	    
	    Ordine update(Long id, String username, String password, String ruolo);
}
