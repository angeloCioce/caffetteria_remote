package com.example.caffetteria.service;

import com.example.caffetteria.model.Utente;
import com.example.caffetteria.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtenteServiceImpl implements UtenteService, UserDetailsService {

    @Autowired
    private UtenteRepository utenteRepository;

    public UtenteServiceImpl(UtenteRepository UtenteRepository, UtenteRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }

    @Override
    public List<Utente> findAll() {
        return utenteRepository.findAll();
    }

    @Override
    public Utente save(Utente utente) {
        return this.utenteRepository.save(utente);
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

    public Utente loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.utenteRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Utente non trovato con username: " + username));
    }
    public void changePassword(Long id, String newPassword){
        Utente utente = utenteRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Utente non trovato"));
        utente.setPassword(newPassword);
        utenteRepository.save(utente);
    }

}
