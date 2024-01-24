package com.example.caffetteria.controller;


import com.example.caffetteria.dto.AuthenticationRequest;
import com.example.caffetteria.dto.AuthenticationResponse;
import com.example.caffetteria.dto.RegisterRequest;
import com.example.caffetteria.dto.UtenteDto;
import com.example.caffetteria.model.Utente;
import com.example.caffetteria.service.AuthenticationService;
import com.example.caffetteria.service.UtenteService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/utente")
@RequiredArgsConstructor
public class UtenteController {

	@Autowired
	private UtenteService ut;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private final AuthenticationService authenticationService;
	
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
		Utente utente = ut.loadUserByUsername(username);
		ut.changePassword(utente.getId_utente(), newPassword);
		String apiResponse = "Password cambiata con successo";
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}


	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(
			@RequestBody RegisterRequest request
	) {

		return ResponseEntity.ok(authenticationService.register(request));
    }

	@PostMapping("/atuhenticate")
	public ResponseEntity<AuthenticationResponse> register(
			@RequestBody AuthenticationRequest request
	) {

		return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
