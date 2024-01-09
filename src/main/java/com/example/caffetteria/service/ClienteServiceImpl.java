package com.example.caffetteria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.caffetteria.model.Cliente;
import com.example.caffetteria.repository.ClienteRepository;


@Service
public class ClienteServiceImpl implements ClienteService{

	@Autowired
	private ClienteRepository clienteRepository;

	
	//lista clienti
	
	@Override
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return clienteRepository.findAll();
	}

	//salva cliente
	
	@Override
	public Cliente save(Cliente cliente) {
		// TODO Auto-generated method stub
		clienteRepository.save(cliente);
		return cliente;
	}

	//trova cliente by id
	
	@Override
	public Optional<Cliente> findById(Long id) {
		// TODO Auto-generated method stub
		return clienteRepository.findById(id);
	}

	//cancella cliente by id
	
	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		clienteRepository.deleteById(id);
	}

	@Override
	public Cliente update(Long id, String nome, String cognome)
	{
		// TODO Auto-generated method stub
		Cliente cliente = clienteRepository.getById(id);
        
            cliente.setNome(nome);
            cliente.setCognome(cognome);

			return cliente;
    }
	
}
	

