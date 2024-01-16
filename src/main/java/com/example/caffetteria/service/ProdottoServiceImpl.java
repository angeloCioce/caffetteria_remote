package com.example.caffetteria.service;

import com.example.caffetteria.model.Cliente;
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
       return prodottoRepository.save(prodotto);
    }

    @Override
    public Prodotto findById(Long id) {

        Optional<Prodotto> result = prodottoRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        else{
            throw new IllegalArgumentException("Prodotto non trovato con id:" + id);
        }
    }

    @Override
    public void delete(Long id) {
        prodottoRepository.deleteById(id);
    }

    @Override
    public Prodotto update(Long id, Prodotto prodottoRequest) {

        Prodotto prodotto = prodottoRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Prodotto non trovato"));

        prodotto.setNome_prodotto(prodottoRequest.getNome_prodotto());
        prodotto.setDescrizione(prodottoRequest.getDescrizione());
        prodotto.setPrezzo_ingrosso(prodottoRequest.getPrezzo_ingrosso());
        prodotto.setPrezzo_dettaglio(prodottoRequest.getPrezzo_dettaglio());
        prodotto.setQuantita(prodottoRequest.getQuantita());
        prodotto.setTipologia(prodottoRequest.getTipologia());

        return prodottoRepository.save(prodotto);

    }
}
