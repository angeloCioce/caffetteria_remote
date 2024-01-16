package com.example.caffetteria.service;

import com.example.caffetteria.model.Cliente;
import com.example.caffetteria.model.Ordine;
import com.example.caffetteria.model.Utente;
import com.example.caffetteria.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtenteServiceImpl implements UtenteService{

    @Autowired
    private UtenteRepository utenteRepository;

    @Override
    public List<Utente> findAll() {
        return utenteRepository.findAll();
    }

    @Override
    public Utente save(Utente utente) {
        return utenteRepository.save(utente);
    }

    @Override
    public Utente findById(Long id) {

        Optional<Utente> result = utenteRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        else{
            throw new IllegalArgumentException("Utente non trovato con id:" + id);
        }
    }

    @Override
    public void delete(Long id) {
        utenteRepository.deleteById(id);
    }

    @Override
    public Utente update(Long id, Utente utenteRequest) {

        Utente utente = utenteRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Utente non trovato"));

        utente.setUsername(utenteRequest.getUsername());
        utente.setPassword(utenteRequest.getPassword());
        utente.setRuolo(utenteRequest.getRuolo());

        return utenteRepository.save(utente);
    }
}
