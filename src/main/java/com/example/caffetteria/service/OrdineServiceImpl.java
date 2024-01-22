package com.example.caffetteria.service;


import com.example.caffetteria.dto.OrdineDto;
import com.example.caffetteria.dto.ProdottoDto;
import com.example.caffetteria.model.*;
import com.example.caffetteria.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;


@Service
public class OrdineServiceImpl implements OrdineService{

	@Autowired
	private OrdineRepository ordineRepository;
	@Autowired
	private UtenteRepository utenteRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ProdottoOrdiniRepository prodottoOrdiniRepository;
	@Autowired
	private ProdottoRepository prodottoRepository;


	@Override
	public List<Ordine> findAll() {

		return ordineRepository.findAll();
	}

	@Override
	public Ordine save(OrdineDto ordineDto) {
		Cliente cliente = clienteRepository.findById(ordineDto.getId_cliente())
				.orElseThrow(() -> new IllegalArgumentException("Cliente non trovato"));
		Utente utente = utenteRepository.findById(ordineDto.getId_utente())
				.orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));

        Double prezzoTotale = 0.0;

		Ordine ordine = Ordine.builder()
				.data_ordine(LocalDateTime.now())
				.prezzo_totale(prezzoTotale)
				.cliente(cliente)
				.utente(utente)
				.build();

		ordineRepository.save(ordine);

		for (ProdottoDto prodottoDto : ordineDto.getProdotti()) {
			Prodotto prodotto = prodottoRepository.findById(prodottoDto.getId_prodotto())
					.orElseThrow(() -> new IllegalArgumentException("Prodotto non trovato"));

			Prodotti_Ordini prodottiOrdini = new Prodotti_Ordini();
			prodottiOrdini.setOrdine(ordine);
			prodottiOrdini.setProdotto(prodotto);
			prodottiOrdini.setQuantita_ordine(prodottoDto.getQuantita_ordine());
			prezzoTotale += prodotto.getPrezzo_dettaglio() * prodottoDto.getQuantita_ordine();

			prodottoOrdiniRepository.save(prodottiOrdini);
		}


		ordine.setPrezzo_totale(prezzoTotale);
		ordineRepository.save(ordine);


		return ordine;
	}


	@Override
	public Ordine findById(Long id) {

		Optional<Ordine> result = ordineRepository.findById(id);
		if(result.isPresent()){
			return result.get();
		}
		else{
			throw new IllegalArgumentException("Ordine non trovato con id:" + id);
		}
	}

	@Override
	public void delete(Long id) {
		ordineRepository.deleteById(id);
	}

	@Override
	public Ordine update(Long id, Ordine ordineRequest) {
		Ordine ordine = ordineRepository.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("Ordine non trovato"));

		ordine.setData_ordine(LocalDateTime.parse(ordineRequest.getData_ordine().toString()));
		ordine.setPrezzo_totale(Double.valueOf(ordineRequest.getPrezzo_totale().toString()));
		return ordineRepository.save(ordine);
	}
}
	

