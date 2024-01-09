package com.example.caffetteria.service;


import com.example.caffetteria.model.Ordine;
import com.example.caffetteria.repository.OrdineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class OrdineServiceImpl implements OrdineService{

	@Autowired
	private OrdineRepository ordinepository;

	@Override
	public List<Ordine> findAll() {
		return ordinepository.findAll();
	}

	@Override
	public Ordine save(Ordine ordine) {
		ordinepository.save(ordine);
		return ordine;
	}

	@Override
	public Optional<Ordine> findById(Long id) {
		return ordinepository.findById(id);
	}

	@Override
	public void delete(Long id) {
		ordinepository.deleteById(id);
	}

	@Override
	public Ordine update(Long id, LocalDateTime data_ordine, Double prezzo_totale) {
		return null;
	}
}
	

