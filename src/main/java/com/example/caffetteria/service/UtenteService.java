package com.example.caffetteria.service;

import com.example.caffetteria.model.Utente;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UtenteService {

	 	List <Utente> findAll();

	    Utente save(Utente utente);

		Utente findById(Long id);

	    void delete(Long id);

		Utente update(Long id, Utente utenteRequest);

		void changePassword(Long id, String newPassword);

		Utente loadUserByUsername(String username);

		Page<Utente> findAllPaginated(int page, int size);

		Page<Utente> findByRolePaginated(String ruolo, int page, int size);

}
