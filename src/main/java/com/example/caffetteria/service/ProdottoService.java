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
	     
	    Prodotto findById(Long id);

	    void delete(Long id);

		Prodotto update(Long id, Prodotto prodottoRequest);
}
