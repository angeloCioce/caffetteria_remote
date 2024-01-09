package com.example.caffetteria.controller;

import com.example.caffetteria.model.Cliente;
import com.example.caffetteria.model.Prodotto;
import com.example.caffetteria.service.ClienteService;
import com.example.caffetteria.service.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prodotto")
public class ProdottoController {

	@Autowired
	private ProdottoService prod;
	
	@GetMapping("/prodottoList")
	public List<Prodotto> getProdotto()
	{
		return prod.findAll();
	}
	
	@GetMapping("/getProdotto/{id_prodotto}")
	public Optional<Prodotto> findProdottoById(@PathVariable("id_prodotto") Long id)
	{
		return prod.findById(id);
	}
	
	@PostMapping("/addProdotto")
	public void saveNewProdotto(Prodotto prodotto)
	{
		prod.save(prodotto);
	}
	
	@DeleteMapping("/deleteProdotto/{id_prodotto}")
	public void deleteProdottoById(@PathVariable("id_prodotto") Long id)
	{
		prod.delete(id);
	}
}
