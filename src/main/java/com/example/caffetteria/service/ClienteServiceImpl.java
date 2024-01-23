package com.example.caffetteria.service;

import java.util.List;
import java.util.Optional;

import com.azure.core.exception.ResourceNotFoundException;
import com.azure.core.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
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
		return clienteRepository.save(cliente);
	}

	//trova cliente by id
	
	@Override
	public Cliente findById(Long id) {
		// TODO Auto-generated method stub
		Optional<Cliente> result = clienteRepository.findById(id);
		if(result.isPresent()){
			return result.get();
		}
		else{
			throw new IllegalArgumentException("Cliente non trovato con id:" + id);
		}
	}

	//cancella cliente by id
	
	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		clienteRepository.deleteById(id);
	}

	@Override
	public Cliente update(Long id, Cliente clienteRequest)
	{
		Cliente cliente = clienteRepository.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("Cliente non trovato"));

			cliente.setNome(clienteRequest.getNome());
			cliente.setCognome(clienteRequest.getCognome());
			return clienteRepository.save(cliente);

    }

	
}
	

