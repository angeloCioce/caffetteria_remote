package com.example.caffetteria.service;

import com.example.caffetteria.dto.OrdineDto;
import com.example.caffetteria.dto.ProdottoDto;
import com.example.caffetteria.model.*;
import com.example.caffetteria.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


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
	@Autowired
	private ModelMapper modelMapper;


	@Override
	public List<OrdineDto> findAll() {
		List<Ordine> ordini = ordineRepository.findAll();
		return ordini.stream().map(this::mapToOrdineDtoWithDetails).collect(Collectors.toList());
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
	public OrdineDto findById(Long id) {
		Ordine ordine = ordineRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Ordine non trovato con id:" + id));
		return mapToOrdineDtoWithDetails(ordine);
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

	private OrdineDto mapToOrdineDtoWithDetails(Ordine ordine) {
		OrdineDto ordineDto = modelMapper.map(ordine, OrdineDto.class);

		// Aggiungi i dettagli dei prodotti
		List<Prodotti_Ordini> prodottiOrdini = prodottoOrdiniRepository.findByOrdine(ordine);
		List<ProdottoDto> prodottiDto = prodottiOrdini.stream()
				.map(po -> {
					ProdottoDto prodottoDto = modelMapper.map(po.getProdotto(), ProdottoDto.class);
					prodottoDto.setQuantita_ordine(po.getQuantita_ordine()); // Assicurati di includere la quantità
					return prodottoDto;
				})
				.collect(Collectors.toList());
		ordineDto.setProdotti(prodottiDto);

		if (ordine.getUtente() != null) {
			ordineDto.setId_utente(ordine.getUtente().getId_utente());
			ordineDto.setUsername_utente(ordine.getUtente().getUsername());
		}

		if (ordine.getCliente() != null) {
			ordineDto.setId_cliente(ordine.getCliente().getId());
			ordineDto.setNome_cliente(ordine.getCliente().getNome());
			ordineDto.setCognome_cliente(ordine.getCliente().getCognome());
		}

		return ordineDto;
	}
}
	

