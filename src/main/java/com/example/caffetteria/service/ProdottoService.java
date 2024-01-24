package com.example.caffetteria.service;

import com.example.caffetteria.model.Prodotto;

import java.util.List;

public interface ProdottoService {

	 	List <Prodotto> findAll();

	    Prodotto save(Prodotto prodotto);
	     
	    Prodotto findById(Long id);

	    void delete(Long id);

		Prodotto update(Long id, Prodotto prodottoRequest);
}
