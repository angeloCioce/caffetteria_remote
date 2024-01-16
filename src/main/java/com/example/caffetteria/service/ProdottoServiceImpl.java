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
    public Prodotto update(Long id, String nome_prodotto, String descrizione, Double prezzo_ingrosso, Double prezzo_dettaglio, Integer quantita, String tipologia) {
        Optional<Prodotto> optionalProdotto = prodottoRepository.findById(id);
        if (optionalProdotto.isPresent()) {
            Prodotto prodotto = optionalProdotto.get();
            prodotto.setNome_prodotto(nome_prodotto);
            prodotto.setDescrizione(descrizione);
            prodotto.setQuantita(quantita);
            prodotto.setPrezzo_ingrosso(prezzo_ingrosso);
            prodotto.setPrezzo_dettaglio(prezzo_dettaglio);
            prodotto.setTipologia(tipologia);

            return prodottoRepository.save(prodotto);
        }
        return null;
    }
}
