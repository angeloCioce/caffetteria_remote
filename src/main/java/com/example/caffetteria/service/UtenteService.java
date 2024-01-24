package com.example.caffetteria.service;

import com.example.caffetteria.model.Utente;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UtenteService {

	 	List <Utente> findAll();

	    Utente save(Utente utente);

		Utente findById(Long id);

	    void delete(Long id);

		Utente update(Long id, Utente utenteRequest);

		void changePassword(Long id, String newPassword);
		Utente loadUserByUsername(String username);

}
