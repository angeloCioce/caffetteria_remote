package com.example.caffetteria.service;

import java.util.List;

import com.example.caffetteria.model.Cliente;

public interface ClienteService {

	 	List <Cliente> findAll();

	    Cliente save(Cliente cliente);

		Cliente findById(Long id);

	    void delete(Long id);

		Cliente update(Long id, Cliente clienteRequest);

}
