package com.example.caffetteria.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.caffetteria.dto.ClienteDto;
import org.modelmapper.ModelMapper;
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
	private ModelMapper modelMapper;

	@Autowired
	private ClienteService cli;
	
	@GetMapping("/clienteList")
	public List<ClienteDto> getClienti()
	{
		return cli.findAll().stream().map(cliente->modelMapper.map(cliente, ClienteDto.class))
				.collect(Collectors.toList());
	}
	
	@GetMapping("/getCliente/{id_cliente}")
	public ResponseEntity<ClienteDto> findClienteById(@PathVariable("id_cliente") Long id)
	{
		Cliente cliente = cli.findById(id);
		ClienteDto clienteResponse = modelMapper.map(cliente, ClienteDto.class);
		return ResponseEntity.ok().body(clienteResponse);
	}
	
	@PostMapping("/addCliente")
	public ResponseEntity<ClienteDto> saveNewCliente(@RequestBody ClienteDto clienteDto)
	{
		Cliente clienteRequest = modelMapper.map(clienteDto, Cliente.class);
		Cliente cliente = cli.save(clienteRequest);
		ClienteDto clienteResponse = modelMapper.map(cliente, ClienteDto.class);
		return  new ResponseEntity<ClienteDto>(clienteResponse, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deleteCliente/{id_cliente}")
	public ResponseEntity<String> deleteClienteById(@PathVariable("id_cliente") Long id)
	{
		cli.delete(id);
		String apiResponse = ("Record deleted successfully");
        return new ResponseEntity<String>(apiResponse, HttpStatus.OK);
    }
	
	@PatchMapping("update/{id_cliente}")
	public ResponseEntity<ClienteDto> updateClienteById(@PathVariable("id_cliente") Long id, @RequestBody ClienteDto clienteDto)
	{
		Cliente clienteRequest = modelMapper.map(clienteDto, Cliente.class);
		Cliente cliente = cli.update(id, clienteRequest);
		ClienteDto clienteResponse = modelMapper.map(cliente, ClienteDto.class);
        return ResponseEntity.ok().body(clienteResponse);
    }
}
