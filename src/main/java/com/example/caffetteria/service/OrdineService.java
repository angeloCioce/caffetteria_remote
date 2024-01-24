package com.example.caffetteria.service;

import com.example.caffetteria.dto.OrdineDto;
import com.example.caffetteria.model.Ordine;

import java.util.List;

public interface OrdineService {

	 	List <OrdineDto> findAll();
		Ordine save(OrdineDto ordineDto);
		OrdineDto findById(Long id);
	    void delete(Long id);
		Ordine update(Long id, Ordine ordineRequest);

}
