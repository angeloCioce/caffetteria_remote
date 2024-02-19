package com.example.caffetteria.repository;

import com.example.caffetteria.userRole.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.caffetteria.model.Utente;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long>{

    Optional<Utente> findByUsername(String username);
    Page<Utente> findByRuolo(UserRole ruolo, Pageable pageable);
}
