package com.example.caffetteria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.caffetteria.model.Cliente;
import com.example.caffetteria.repository.ClienteRepository;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteRepository cli;
	
	@PostMapping("/addCliente")
	public Cliente newCliente(@RequestBody Cliente cliente)
	{
		return cli.save(cliente);
	}
	
	@GetMapping("/getClienti")
	public List<Cliente> getClienti()
	{
		return cli.findAll();
	}
	
	@GetMapping("/getCliente/{id_cliente}")
	public Cliente getClienteById(@PathVariable Long id_cliente)
	{
		return cli.findById(id_cliente).get();
	}
	
	@DeleteMapping("/deleteCliente/{id}")
	void deleteClienteById(@PathVariable Long id_cliente)
	{
		cli.deleteById(id_cliente);
	}
	
	@PutMapping("/updateCliente/{id_cliente}")
	public Cliente updateCliente(@RequestBody Cliente newCliente, @PathVariable Long id_cliente)
	{
		cli.deleteById(id_cliente);
		return cli.save(newCliente);
	}
}
