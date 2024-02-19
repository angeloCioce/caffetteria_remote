package com.example.caffetteria.controller;

import com.example.caffetteria.config.PaginationRequest;
import com.example.caffetteria.dto.OrdineDto;
import com.example.caffetteria.model.Ordine;
import com.example.caffetteria.service.OrdineService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordine")
public class OrdineController {

	@Autowired
	private OrdineService ordineService;
	@Autowired
	private ModelMapper modelMapper;
	@PreAuthorize("hasAnyAuthority('ADMIN', 'MANUTENTORE', 'DIPENDENTE')")
	@GetMapping("/OrdineList")
	public List<OrdineDto> getOrdine()
	{

		return ordineService.findAll();
	}
	@PreAuthorize("hasAnyAuthority('ADMIN', 'MANUTENTORE', 'DIPENDENTE')")
	@GetMapping("/getOrdine/{id_ordine}")
	public ResponseEntity<OrdineDto> findOrdineById(@PathVariable("id_ordine") Long id)
	{
		OrdineDto ordineResponse = ordineService.findById(id);
		return ResponseEntity.ok().body(ordineResponse);
	}
	@PreAuthorize("hasAnyAuthority('ADMIN', 'MANUTENTORE', 'DIPENDENTE')")
	@PostMapping("/addOrdine")
	public ResponseEntity<OrdineDto> saveNewOrdine(@RequestBody OrdineDto ordineDto) {
		Ordine ordine = ordineService.save(ordineDto);
		return new ResponseEntity<>(ordineDto, HttpStatus.CREATED);
	}
	@PreAuthorize("hasAnyAuthority('ADMIN', 'MANUTENTORE', 'DIPENDENTE')")
	@DeleteMapping("/deleteOrdine/{id_ordine}")
	public ResponseEntity<String> deleteOrdineById(@PathVariable("id_ordine") Long id)
	{
		ordineService.delete(id);
		String apiResponse = ("Record deleted successfully");
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}
	@PreAuthorize("hasAnyAuthority('ADMIN', 'MANUTENTORE', 'DIPENDENTE')")
	@PatchMapping("update/{id_ordine}")
	public ResponseEntity<OrdineDto> updateOrdineById(@PathVariable("id_ordine") Long id, @RequestBody OrdineDto ordineDto) {
		Ordine ordineRequest = modelMapper.map(ordineDto, Ordine.class);
		Ordine ordine = ordineService.update(id, ordineRequest);
		OrdineDto ordineResponse = modelMapper.map(ordine, OrdineDto.class);
		return ResponseEntity.ok().body(ordineResponse);
	}
	@PreAuthorize("hasAnyAuthority('ADMIN', 'MANUTENTORE', 'DIPENDENTE')")
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
	@PreAuthorize("hasAnyAuthority('ADMIN', 'MANUTENTORE', 'DIPENDENTE')")
	@PostMapping("/ordini/month-year")
	public ResponseEntity<?> getOrdersByMonthAndYear(@RequestBody PaginationRequest paginationRequest) {
		int month = paginationRequest.getMonth();
		int year = paginationRequest.getYear();
		int page = paginationRequest.getPage();
		int size = paginationRequest.getSize();
		Page<OrdineDto> ordini = ordineService.getOrdersByMonthAndYear(month, year, page, size);

		if (ordini.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nessun ordine trovato per il mese o l'anno specificati");
		} else {
			return ResponseEntity.ok(ordini);
		}
	}
}
