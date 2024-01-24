package com.example.caffetteria.controller;

import com.example.caffetteria.dto.OrdineDto;
import com.example.caffetteria.model.Ordine;
import com.example.caffetteria.service.OrdineService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordine")
public class OrdineController {

	@Autowired
	private OrdineService ordineService;
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/OrdineList")
	public List<OrdineDto> getOrdine()
	{

		return ordineService.findAll();
	}
	
	@GetMapping("/getOrdine/{id_ordine}")
	public ResponseEntity<OrdineDto> findOrdineById(@PathVariable("id_ordine") Long id)
	{
		OrdineDto ordineResponse = ordineService.findById(id);
		return ResponseEntity.ok().body(ordineResponse);
	}

	@PostMapping("/addOrdine")
	public ResponseEntity<OrdineDto> saveNewOrdine(@RequestBody OrdineDto ordineDto) {
		Ordine ordine = ordineService.save(ordineDto);
		return new ResponseEntity<>(ordineDto, HttpStatus.CREATED);
	}

	@DeleteMapping("/deleteOrdine/{id_ordine}")
	public ResponseEntity<String> deleteOrdineById(@PathVariable("id_ordine") Long id)
	{
		ordineService.delete(id);
		String apiResponse = ("Record deleted successfully");
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}
	@PatchMapping("update/{id_ordine}")
	public ResponseEntity<OrdineDto> updateOrdineById(@PathVariable("id_ordine") Long id, @RequestBody OrdineDto ordineDto) {
		Ordine ordineRequest = modelMapper.map(ordineDto, Ordine.class);
		Ordine ordine = ordineService.update(id, ordineRequest);
		OrdineDto ordineResponse = modelMapper.map(ordine, OrdineDto.class);
		return ResponseEntity.ok().body(ordineResponse);
	}
}
