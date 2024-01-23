package com.example.caffetteria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.caffetteria.model.Utente;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long>{

    Optional<Utente> findByUsername(String username);
}
