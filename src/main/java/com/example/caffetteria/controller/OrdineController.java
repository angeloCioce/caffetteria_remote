package com.example.caffetteria.controller;

import com.example.caffetteria.dto.ClienteDto;
import com.example.caffetteria.dto.OrdineDto;
import com.example.caffetteria.dto.UtenteDto;
import com.example.caffetteria.model.Cliente;
import com.example.caffetteria.model.Ordine;
import com.example.caffetteria.model.Prodotto;
import com.example.caffetteria.model.Utente;
import com.example.caffetteria.service.ClienteService;
import com.example.caffetteria.service.OrdineService;
import com.example.caffetteria.service.UtenteService;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ordine")
public class OrdineController {

	@Autowired
	private OrdineService ord;
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/OrdineList")
	public List<OrdineDto> getOrdine()
	{

		return ord.findAll().stream().map(ordine->modelMapper.map(ordine, OrdineDto.class))
				.collect(Collectors.toList());
	}
	
	@GetMapping("/getOrdine/{id_ordine}")
	public ResponseEntity<OrdineDto> findOrdineById(@PathVariable("id_ordine") Long id)
	{

		Ordine ordine = ord.findById(id);
		OrdineDto ordineResponse = modelMapper.map(ordine, OrdineDto.class);
		return ResponseEntity.ok().body(ordineResponse);
	}
	
	@PostMapping("/addOrdine")
	public ResponseEntity<OrdineDto> saveNewOrdine(@RequestBody OrdineDto ordineDto)
	{
		Ordine ordineRequest = modelMapper.map(ordineDto, Ordine.class);
		Ordine ordine = ord.save(ordineRequest,
				ordineDto.getId_cliente(),
				ordineDto.getId_utente(),
				ordineDto.getId_prodotto(),
				ordineDto.getQuantita_ordine());
		OrdineDto ordineResponse = modelMapper.map(ordine, OrdineDto.class);
		return  new ResponseEntity<OrdineDto>(ordineResponse, HttpStatus.CREATED);
	}

	@DeleteMapping("/deleteOrdine/{id_ordine}")
	public ResponseEntity<String> deleteOrdineById(@PathVariable("id_ordine") Long id)
	{
		ord.delete(id);
		String apiResponse = ("Record deleted successfully");
		return new ResponseEntity<String>(apiResponse, HttpStatus.OK);
	}
	@PatchMapping("update/{id_ordine}")
	public ResponseEntity<OrdineDto> updateOrdineById(@PathVariable("id_ordine") Long id, @RequestBody OrdineDto ordineDto) {
		Ordine ordineRequest = modelMapper.map(ordineDto, Ordine.class);
		Ordine ordine = ord.update(id, ordineRequest);
		OrdineDto ordineResponse = modelMapper.map(ordine, OrdineDto.class);
		return ResponseEntity.ok().body(ordineResponse);
	}
}
