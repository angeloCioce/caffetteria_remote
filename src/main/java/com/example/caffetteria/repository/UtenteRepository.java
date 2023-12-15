package com.example.caffetteria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.caffetteria.model.Utente;

public interface UtenteRepository extends JpaRepository<Utente, Long>{

}
