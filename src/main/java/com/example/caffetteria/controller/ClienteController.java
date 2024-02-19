package com.example.caffetteria.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.example.caffetteria.dto.ClienteDto;
import com.example.caffetteria.dto.UtenteDto;
import com.example.caffetteria.model.Utente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
	@PreAuthorize("hasAnyAuthority('ADMIN', 'MANUTENTORE')")
	@GetMapping("/clienteList")
	public List<ClienteDto> getClienti()
	{
		return cli.findAll().stream().map(cliente->modelMapper.map(cliente, ClienteDto.class))
				.collect(Collectors.toList());
	}
	@PreAuthorize("hasAnyAuthority('ADMIN', 'MANUTENTORE')")
	@GetMapping("/getCliente/{id_cliente}")
	public ResponseEntity<ClienteDto> findClienteById(@PathVariable("id_cliente") Long id)
	{
		Cliente cliente = cli.findById(id);
		ClienteDto clienteResponse = modelMapper.map(cliente, ClienteDto.class);
		return ResponseEntity.ok().body(clienteResponse);
	}
	@PreAuthorize("hasAnyAuthority('ADMIN', 'MANUTENTORE')")
	@PostMapping("/addCliente")
	public ResponseEntity<ClienteDto> saveNewCliente(@RequestBody ClienteDto clienteDto)
	{
		Cliente clienteRequest = modelMapper.map(clienteDto, Cliente.class);
		Cliente cliente = cli.save(clienteRequest);
		ClienteDto clienteResponse = modelMapper.map(cliente, ClienteDto.class);
		return new ResponseEntity<>(clienteResponse, HttpStatus.CREATED);
	}
	@PreAuthorize("hasAnyAuthority('ADMIN', 'MANUTENTORE')")
	@DeleteMapping("/deleteCliente/{id_cliente}")
	public ResponseEntity<String> deleteClienteById(@PathVariable("id_cliente") Long id)
	{
		cli.delete(id);
		String apiResponse = ("Record deleted successfully");
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
	@PreAuthorize("hasAnyAuthority('ADMIN', 'MANUTENTORE')")
	@PatchMapping("update/{id_cliente}")
	public ResponseEntity<ClienteDto> updateClienteById(@PathVariable("id_cliente") Long id, @RequestBody ClienteDto clienteDto)
	{
		Cliente clienteRequest = modelMapper.map(clienteDto, Cliente.class);
		Cliente cliente = cli.update(id, clienteRequest);
		ClienteDto clienteResponse = modelMapper.map(cliente, ClienteDto.class);
        return ResponseEntity.ok().body(clienteResponse);
    }

	@PreAuthorize("hasAnyAuthority('ADMIN', 'DIPENDENTE', 'MANUTENTORE')")
	@GetMapping("/pageableCliente")
	public List<ClienteDto> getPageableCliente(@RequestParam(defaultValue = "0") int page,
											 @RequestParam(defaultValue = "5") int size) {
		Page<Cliente> clientePage = cli.findAllPaginated(page, size);

		return clientePage.getContent().stream()
				.map(cliente -> modelMapper.map(cliente, ClienteDto.class))
				.collect(Collectors.toList());
	}
}
