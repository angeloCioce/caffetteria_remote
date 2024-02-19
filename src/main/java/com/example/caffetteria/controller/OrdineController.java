package com.example.caffetteria.controller;

import com.example.caffetteria.config.PaginationRequest;
import com.example.caffetteria.dto.OrdineDto;
import com.example.caffetteria.dto.ProdottoDto;
import com.example.caffetteria.model.Ordine;
import com.example.caffetteria.model.Prodotto;
import com.example.caffetteria.service.OrdineService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ordine")
public class OrdineController {

	@Autowired
	private OrdineService ordineService;
	@Autowired
	private ModelMapper modelMapper;
	@PreAuthorize("hasAnyAuthority('ADMIN', 'MANUTENTORE')")
	@GetMapping("/OrdineList")
	public List<OrdineDto> getOrdine()
	{

		return ordineService.findAll();
	}
	@PreAuthorize("hasAnyAuthority('ADMIN', 'MANUTENTORE')")
	@GetMapping("/getOrdine/{id_ordine}")
	public ResponseEntity<OrdineDto> findOrdineById(@PathVariable("id_ordine") Long id)
	{
		OrdineDto ordineResponse = ordineService.findById(id);
		return ResponseEntity.ok().body(ordineResponse);
	}
	@PreAuthorize("hasAnyAuthority('ADMIN', 'MANUTENTORE')")
	@PostMapping("/addOrdine")
	public ResponseEntity<OrdineDto> saveNewOrdine(@RequestBody OrdineDto ordineDto) {
		Ordine ordine = ordineService.save(ordineDto);
		return new ResponseEntity<>(ordineDto, HttpStatus.CREATED);
	}
	@PreAuthorize("hasAnyAuthority('ADMIN', 'MANUTENTORE')")
	@DeleteMapping("/deleteOrdine/{id_ordine}")
	public ResponseEntity<String> deleteOrdineById(@PathVariable("id_ordine") Long id)
	{
		ordineService.delete(id);
		String apiResponse = ("Record deleted successfully");
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}
	@PreAuthorize("hasAnyAuthority('ADMIN', 'MANUTENTORE')")
	@PatchMapping("update/{id_ordine}")
	public ResponseEntity<OrdineDto> updateOrdineById(@PathVariable("id_ordine") Long id, @RequestBody OrdineDto ordineDto) {
		Ordine ordineRequest = modelMapper.map(ordineDto, Ordine.class);
		Ordine ordine = ordineService.update(id, ordineRequest);
		OrdineDto ordineResponse = modelMapper.map(ordine, OrdineDto.class);
		return ResponseEntity.ok().body(ordineResponse);
	}


//	@PostMapping("/ordini/year")
//	public Page<OrdineDto> getOrdersByYear(@RequestBody PaginationRequest paginationRequest) {
//		int year = paginationRequest.getYear();
//		int page = paginationRequest.getPage();
//		int size = paginationRequest.getSize();
//		return ordineService.getOrdersByYear(year, page, size);
//	}
//
//	@PostMapping("/ordini/month-year")
//	public Page<OrdineDto> getOrdersByMonthAndYear(@RequestBody PaginationRequest paginationRequest) {
//		int month = paginationRequest.getMonth();
//		int year = paginationRequest.getYear();
//		int page = paginationRequest.getPage();
//		int size = paginationRequest.getSize();
//		return ordineService.getOrdersByMonthAndYear(month, year, page, size);
//	}

	@PostMapping("/ordini/year")
	public ResponseEntity<?> getOrdersByYear(@RequestBody PaginationRequest paginationRequest) {
		int year = paginationRequest.getYear();
		int page = paginationRequest.getPage();
		int size = paginationRequest.getSize();
		Page<OrdineDto> ordini = ordineService.getOrdersByYear(year, page, size);

		if (ordini.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nessun ordine trovato per l'anno specificato");
		} else {
			return ResponseEntity.ok(ordini);
		}
	}

	@PostMapping("/ordini/month-year")
	public ResponseEntity<?> getOrdersByMonthAndYear(@RequestBody PaginationRequest paginationRequest) {
		int month = paginationRequest.getMonth();
		int year = paginationRequest.getYear();
		int page = paginationRequest.getPage();
		int size = paginationRequest.getSize();
		Page<OrdineDto> ordini = ordineService.getOrdersByMonthAndYear(month, year, page, size);

		if (ordini.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nessun ordine trovato per il mese e l'anno specificati");
		} else {
			return ResponseEntity.ok(ordini);
		}
	}
}
