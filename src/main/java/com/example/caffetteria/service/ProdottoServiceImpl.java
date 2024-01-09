package com.example.caffetteria.service;

import com.example.caffetteria.model.Ordine;
import com.example.caffetteria.model.Prodotto;
import com.example.caffetteria.repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdottoServiceImpl implements ProdottoService{

    @Autowired
    private ProdottoRepository prodottoRepository;

    @Override
    public List<Prodotto> findAll() {
        return prodottoRepository.findAll();
    }

    @Override
    public Prodotto save(Prodotto prodotto) {
        prodottoRepository.save(prodotto);
        return prodotto;
    }

    @Override
    public Optional<Prodotto> findById(Long id) {
        return prodottoRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        prodottoRepository.deleteById(id);
    }

    @Override
    public Ordine update(Long id, String nome_prodotto, String descrizione, Double prezzo_ingrosso, Double prezzo_dettaglio, Integer quantita, String tipologia) {
        return null;
    }
}
