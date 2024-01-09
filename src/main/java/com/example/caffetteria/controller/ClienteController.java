package com.example.caffetteria.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.caffetteria.model.Cliente;
import com.example.caffetteria.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteService cli;
	
	@GetMapping("/clienteList")
	public List<Cliente> getClienti()
	{
		return cli.findAll();
	}
	
	@GetMapping("/getCliente/{id_cliente}")
	public Optional<Cliente> findClienteById(@PathVariable("id_cliente") Long id)
	{
		return cli.findById(id);
	}
	
	@PostMapping("/addCliente")
	public void saveNewCliente(Cliente cliente)
	{
		cli.save(cliente);
	}
	
	@DeleteMapping("/deleteCliente/{id_cliente}")
	public void deleteClienteById(@PathVariable("id_cliente") Long id)
	{
		cli.delete(id);
	}
	
	@PatchMapping("update/{id_cliente}")
	public Cliente updateClienteById(@PathVariable("id_cliente") Long id, @RequestBody String nome, @RequestBody String cognome)
	{
		Optional<Cliente> cliente = cli.findById(id);
		if(cliente.isPresent())
		{
			Cliente clientePresente = cliente.get();
			clientePresente.setNome(nome);
			clientePresente.setCognome(cognome);
			return cli.save(clientePresente);
		}

		else
			return null;


	}
}
