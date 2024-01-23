package com.example.caffetteria.controller;

import com.example.caffetteria.dto.ClienteDto;
import com.example.caffetteria.dto.UtenteDto;
import com.example.caffetteria.model.Cliente;
import com.example.caffetteria.model.Utente;
import com.example.caffetteria.service.UtenteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/utente")
public class UtenteController {

	@Autowired
	private UtenteService ut;
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/utenteList")
	public List<UtenteDto> getUtente()
	{

		return ut.findAll().stream().map(utente->modelMapper.map(utente, UtenteDto.class))
				.collect(Collectors.toList());
	}
	
	@GetMapping("/getUtente/{id_utente}")
	public ResponseEntity<UtenteDto> findUtenteById(@PathVariable("id_utente") Long id)
	{

		Utente utente = ut.findById(id);
		UtenteDto utenteResponse = modelMapper.map(utente, UtenteDto.class);
		return ResponseEntity.ok().body(utenteResponse);
	}
	
	@PostMapping("/addUtente")
	public ResponseEntity<UtenteDto> saveNewUtente(@RequestBody UtenteDto utenteDto)
	{

		Utente utenteRequest = modelMapper.map(utenteDto, Utente.class);
		Utente utente = ut.save(utenteRequest);
		UtenteDto utenteResponse = modelMapper.map(utente, UtenteDto.class);
		return  new ResponseEntity<UtenteDto>(utenteResponse, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deleteUtente/{id_utente}")
	public ResponseEntity<String> deleteUtenteById(@PathVariable("id_utente") Long id)
	{

		ut.delete(id);
		String apiResponse = ("Record deleted successfully");
		return new ResponseEntity<String>(apiResponse, HttpStatus.OK);
	}

	@PatchMapping("update/{id_utente}")
	public ResponseEntity<UtenteDto> updateUtenteById(@PathVariable("id_utente") Long id, @RequestBody UtenteDto utenteDto)
	{
		Utente utenteRequest = modelMapper.map(utenteDto, Utente.class);
		Utente utente = ut.update(id, utenteRequest);
		UtenteDto utenteResponse = modelMapper.map(utente, UtenteDto.class);
		return ResponseEntity.ok().body(utenteResponse);
	}

	@PatchMapping("/changePassword")
	public ResponseEntity<String> changePasswordByUsername(@RequestParam String username, @RequestBody String newPassword) {
		Utente utente = ut.findByUsername(username);
		ut.changePassword(utente.getId_utente(), newPassword);
		String apiResponse = "Password cambiata con successo";
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

//	@PatchMapping("/changeUsername")
//	public ResponseEntity<String> changeUsername(@RequestBody ChangeUsernameRequest request) {
//		Utente utente = ut.changeUsername(request.getOldUsername(), request.getNewUsername(), request.getPassword());
//		String apiResponse = "Username cambiato con successo per l'utente con username: " + request.getOldUsername();
//		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
//	}
}
