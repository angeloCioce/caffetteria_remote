package com.example.caffetteria.service;

import com.example.caffetteria.model.Ordine;

import java.util.List;

public interface OrdineService {

	 	List <Ordine> findAll();
		Ordine save(Ordine ordine, Long idCliente, Long idUtente, Long idProdotto, Integer quantitaOrdine);
		Ordine findById(Long id);
	    void delete(Long id);
		Ordine update(Long id, Ordine ordineRequest);

}
