package com.example.caffetteria.service;

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
        utenteRepository.save(utente);
        return utente;
    }

    @Override
    public Optional<Utente> findById(Long id) {
        return utenteRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        utenteRepository.deleteById(id);
    }

    @Override
    public Ordine update(Long id, String username, String password, String ruolo) {
        return null;
    }
}
