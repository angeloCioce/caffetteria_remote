package com.example.caffetteria.service;


import com.example.caffetteria.model.*;
import com.example.caffetteria.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public Ordine save(Ordine ordine, Long idCliente, Long idUtente, Long idProdotto, Integer quantitaOrdine) {
		Cliente cliente = clienteRepository.findById(idCliente)
				.orElseThrow(() -> new IllegalArgumentException("Cliente non trovato"));
		Utente utente = utenteRepository.findById(idUtente)
				.orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));
		Prodotto prodotto = prodottoRepository.findById(idProdotto)
				.orElseThrow(() -> new IllegalArgumentException("Prodotto non trovato"));

		ordine.setCliente(cliente);
		ordine.setUtente(utente);


		Prodotti_Ordini prodottiOrdini = new Prodotti_Ordini();
		prodottiOrdini.setOrdine(ordine);
		prodottiOrdini.setProdotto(prodotto);
		prodottiOrdini.setQuantita_ordine(quantitaOrdine);

		ordine.setPrezzo_totale(prodotto.getPrezzo_dettaglio()*quantitaOrdine);

		ordineRepository.save(ordine);
		prodottoOrdiniRepository.save(prodottiOrdini);


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

		ordine.setData_ordine(ordineRequest.getData_ordine());
		ordine.setPrezzo_totale(ordineRequest.getPrezzo_totale());
		return ordineRepository.save(ordine);
	}
}
	

