package com.example.caffetteria.controller;

import com.example.caffetteria.model.Utente;
import com.example.caffetteria.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/utente")
public class UtenteController {

	@Autowired
	private UtenteService ut;
	
	@GetMapping("/utenteList")
	public List<Utente> getUtente()
	{
		return ut.findAll();
	}
	
	@GetMapping("/getUtente/{id_utente}")
	public Optional<Utente> findUtenteById(@PathVariable("id_utente") Long id)
	{
		return ut.findById(id);
	}
	
	@PostMapping("/addUtente")
	public void saveNewUtente(Utente utente)
	{
		ut.save(utente);
	}
	
	@DeleteMapping("/deleteUtente/{id_utente}")
	public void deleteUtenteById(@PathVariable("id_utente") Long id)
	{
		ut.delete(id);
	}
}
