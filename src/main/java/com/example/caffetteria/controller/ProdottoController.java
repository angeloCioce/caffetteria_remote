package com.example.caffetteria.controller;

import com.example.caffetteria.model.Cliente;
import com.example.caffetteria.model.Ordine;
import com.example.caffetteria.model.Prodotto;
import com.example.caffetteria.service.ClienteService;
import com.example.caffetteria.service.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
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

	@PatchMapping("update/{id_prodotto}")
	public Prodotto updateProdottoById(@PathVariable("id_prodotto") Long id, @RequestBody Map<String, String> request)
	{
		String nome_prodotto = request.get("nome_prodotto");
		String descrizione = request.get("descrizione");
		String tipologia = request.get("tipologia");

		Optional<Prodotto> optionalProdotto = prod.findById(id);
		if (optionalProdotto.isPresent()) {
			Prodotto prodottoPresente = optionalProdotto.get();
			prodottoPresente.setNome_prodotto(nome_prodotto);
			prodottoPresente.setDescrizione(descrizione);
			prodottoPresente.setTipologia(tipologia);

			if (request.containsKey("prezzo_ingrosso")) {
				String prezzoIngrosso = request.get("prezzo_ingrosso").toString();
				prodottoPresente.setPrezzo_ingrosso(Double.valueOf(prezzoIngrosso));
			}
			if (request.containsKey("prezzo_dettaglio")) {
				String prezzoDettaglio = request.get("prezzo_dettaglio").toString();
				prodottoPresente.setPrezzo_dettaglio(Double.valueOf(prezzoDettaglio));
			}
			if(request.containsKey("quantita")) {
				String quantita = request.get("quantita").toString();
				prodottoPresente.setQuantita(Integer.valueOf(quantita));
			}
			return prod.save(prodottoPresente);
		}
		return null;

	}
}
