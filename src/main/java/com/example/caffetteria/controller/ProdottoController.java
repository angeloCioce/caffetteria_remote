package com.example.caffetteria.controller;

import com.example.caffetteria.dto.ProdottoDto;
import com.example.caffetteria.model.Prodotto;
import com.example.caffetteria.service.ProdottoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/prodotto")
public class ProdottoController {

	@Autowired
	private ProdottoService prod;

	@Autowired
	private ModelMapper modelMapper;
	@PreAuthorize("hasAnyAuthority('ADMIN', 'DIPENDENTE', 'MANUTENTORE')")
	@GetMapping("/prodottoList")
	public List<ProdottoDto> getProdotto()
	{

		return prod.findAll().stream().map(prodotto->modelMapper.map(prodotto, ProdottoDto.class))
				.collect(Collectors.toList());
	}
	@PreAuthorize("hasAnyAuthority('ADMIN', 'DIPENDENTE', 'MANUTENTORE')")
	@GetMapping("/getProdotto/{id_prodotto}")
	public ResponseEntity<ProdottoDto> findProdottoById(@PathVariable("id_prodotto") Long id)
	{

		Prodotto prodotto = prod.findById(id);
		ProdottoDto prodottoResponse = modelMapper.map(prodotto, ProdottoDto.class);
		return ResponseEntity.ok().body(prodottoResponse);
	}
	@PreAuthorize("hasAnyAuthority('ADMIN', 'DIPENDENTE', 'MANUTENTORE')")
	@PostMapping("/addProdotto")
	public ResponseEntity<ProdottoDto> saveNewProdotto(@RequestBody ProdottoDto prodottoDto)
	{
		Prodotto prodottoRequest = modelMapper.map(prodottoDto, Prodotto.class);
		Prodotto prodotto = prod.save(prodottoRequest);
		ProdottoDto prodottoResponse = modelMapper.map(prodotto, ProdottoDto.class);
		return new ResponseEntity<>(prodottoResponse, HttpStatus.CREATED);
	}
	@PreAuthorize("hasAnyAuthority('ADMIN', 'DIPENDENTE', 'MANUTENTORE')")
	@DeleteMapping("/deleteProdotto/{id_prodotto}")
	public ResponseEntity<String> deleteProdottoById(@PathVariable("id_prodotto") Long id)
	{
		prod.delete(id);
		String apiResponse = ("Record deleted successfully");
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}
	@PreAuthorize("hasAnyAuthority('ADMIN', 'DIPENDENTE', 'MANUTENTORE')")
	@PatchMapping("update/{id_prodotto}")
	public ResponseEntity<ProdottoDto> updateProdottoById(@PathVariable("id_prodotto") Long id, @RequestBody ProdottoDto prodottoDto)
	{
		Prodotto prodottoRequest = modelMapper.map(prodottoDto, Prodotto.class);
		Prodotto prodotto = prod.update(id, prodottoRequest);
		ProdottoDto prodottoResponse = modelMapper.map(prodotto, ProdottoDto.class);
		return ResponseEntity.ok().body(prodottoResponse);
	}
	@PreAuthorize("hasAnyAuthority('ADMIN', 'DIPENDENTE', 'MANUTENTORE')")
	@GetMapping("/pageableProdotto")
	public List<ProdottoDto> getPageableProdotto(@RequestParam(defaultValue = "0") int page,
										 @RequestParam(defaultValue = "5") int size) {
		Page<Prodotto> prodottoPage = prod.findAllPaginated(page, size);

		return prodottoPage.getContent().stream()
				.map(prodotto -> modelMapper.map(prodotto, ProdottoDto.class))
				.collect(Collectors.toList());
	}

	@PreAuthorize("hasAnyAuthority('ADMIN', 'DIPENDENTE', 'MANUTENTORE')")
	@PostMapping("/prodottoByTipologia")
	public ResponseEntity<?> getProdottoByTipologia(@RequestBody ProdottoDto request) {
		String tipologia = request.getTipologia();
		if (tipologia == null || tipologia.isEmpty()) {
			return ResponseEntity.badRequest().body("Devi specificare una tipologia.");
		}

		Page<Prodotto> prodottoPage = prod.findByTipologiaPaginated(tipologia, request.getPage(), request.getSize());
		if (prodottoPage.isEmpty()) {
			return ResponseEntity.badRequest().body("Ops, nessun prodotto trovato.");
		}

		List<ProdottoDto> prodottoDtoList = prodottoPage.getContent().stream()
				.map(prodotto -> modelMapper.map(prodotto, ProdottoDto.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok().body(prodottoDtoList);
	}
}
