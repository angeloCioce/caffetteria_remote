package com.example.caffetteria.service;

import java.util.List;
import java.util.Optional;

import com.example.caffetteria.model.Cliente;

public interface ClienteService {

	 	List <Cliente> findAll();

	    Cliente save(Cliente cliente);
	     
	    Optional <Cliente> findById(Long id);

	    void delete(Long id);
	    
	    Cliente update(Long id,  String nome, String cognome);
}
