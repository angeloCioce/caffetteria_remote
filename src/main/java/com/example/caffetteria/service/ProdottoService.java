package com.example.caffetteria.service;

import com.example.caffetteria.model.Prodotto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProdottoService {

	 	List <Prodotto> findAll();

	    Prodotto save(Prodotto prodotto);
	     
	    Prodotto findById(Long id);

	    void delete(Long id);

		Prodotto update(Long id, Prodotto prodottoRequest);

		Page<Prodotto> findAllPaginated(int page, int size);

		Page<Prodotto> findByTipologiaPaginated(String tipologia, int page, int size);
}
