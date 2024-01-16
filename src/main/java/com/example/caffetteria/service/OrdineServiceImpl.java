package com.example.caffetteria.service;


import com.example.caffetteria.model.Cliente;
import com.example.caffetteria.model.Ordine;
import com.example.caffetteria.model.Utente;
import com.example.caffetteria.repository.ClienteRepository;
import com.example.caffetteria.repository.OrdineRepository;
import com.example.caffetteria.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

	@Override
	public List<Ordine> findAll() {

		return ordineRepository.findAll();
	}

	@Override
	public Ordine save(Ordine ordine, Long idCliente, Long idUtente) {
		Cliente cliente = clienteRepository.findById(idCliente)
				.orElseThrow(() -> new IllegalArgumentException("Cliente non trovato"));
		Utente utente = utenteRepository.findById(idUtente)
				.orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));

		ordine.setCliente(cliente);
		ordine.setUtente(utente);
		return ordineRepository.save(ordine);
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
	

