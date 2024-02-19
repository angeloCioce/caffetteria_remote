package com.example.caffetteria.controller;


import com.example.caffetteria.dto.*;
import com.example.caffetteria.exceptionhandler.ErrorResponse;
import com.example.caffetteria.exceptionhandler.InvalidPasswordException;
import com.example.caffetteria.model.Utente;
import com.example.caffetteria.service.AuthenticationService;
import com.example.caffetteria.service.UtenteService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
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

	@PreAuthorize("hasAnyAuthority('ADMIN', 'DIPENDENTE', 'MANUTENTORE')")
	@GetMapping("/utenteList")
	public List<UtenteDto> getUtente()
	{

		return ut.findAll().stream().map(utente->modelMapper.map(utente, UtenteDto.class))
				.collect(Collectors.toList());
	}
	@PreAuthorize("hasAnyAuthority('ADMIN', 'DIPENDENTE', 'MANUTENTORE')")
	@GetMapping("/getUtente/{id_utente}")
	public ResponseEntity<UtenteDto> findUtenteById(@PathVariable("id_utente") Long id)
	{

		Utente utente = ut.findById(id);
		UtenteDto utenteResponse = modelMapper.map(utente, UtenteDto.class);
		return ResponseEntity.ok().body(utenteResponse);
	}
	@PreAuthorize("hasAnyAuthority('ADMIN', 'DIPENDENTE', 'MANUTENTORE')")
	@PostMapping("/addUtente")
	public ResponseEntity<UtenteDto> saveNewUtente(@RequestBody UtenteDto utenteDto)
	{

		Utente utenteRequest = modelMapper.map(utenteDto, Utente.class);
		Utente utente = ut.save(utenteRequest);
		UtenteDto utenteResponse = modelMapper.map(utente, UtenteDto.class);
		return new ResponseEntity<>(utenteResponse, HttpStatus.CREATED);
	}
	@PreAuthorize("hasAnyAuthority('ADMIN', 'DIPENDENTE', 'MANUTENTORE')")
	@DeleteMapping("/deleteUtente/{id_utente}")
	public ResponseEntity<String> deleteUtenteById(@PathVariable("id_utente") Long id)
	{

		ut.delete(id);
		String apiResponse = ("Record deleted successfully");
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}
	@PreAuthorize("hasAnyAuthority('ADMIN', 'DIPENDENTE', 'MANUTENTORE')")
	@PatchMapping("update/{id_utente}")
	public ResponseEntity<UtenteDto> updateUtenteById(@PathVariable("id_utente") Long id, @RequestBody UtenteDto utenteDto)
	{
		Utente utenteRequest = modelMapper.map(utenteDto, Utente.class);
		Utente utente = ut.update(id, utenteRequest);
		UtenteDto utenteResponse = modelMapper.map(utente, UtenteDto.class);
		return ResponseEntity.ok().body(utenteResponse);
	}
	@PreAuthorize("hasAnyAuthority('ADMIN', 'DIPENDENTE', 'MANUTENTORE')")
	@PatchMapping("/changePassword")
	public ResponseEntity<String> changePasswordByUsername(@RequestParam String username, @RequestBody String newPassword) {
		Utente utente = ut.loadUserByUsername(username);
		ut.changePassword(utente.getId_utente(), newPassword);
		String apiResponse = "Password cambiata con successo";
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}


	@PostMapping("/register")
	public ResponseEntity<?> register(
			@Valid @RequestBody RegisterRequest request, BindingResult result
	) {

		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body("Errore di validazione: " + result.getFieldError().getDefaultMessage());
		}

		return ResponseEntity.ok(authenticationService.register(request));
    }

	@ExceptionHandler(InvalidPasswordException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponse> handleInvalidPasswordException(InvalidPasswordException  ex) {
		ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
		return ResponseEntity.badRequest().body(errorResponse);
	}

	@ExceptionHandler(BadCredentialsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException ex) {
		ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
		return ResponseEntity.badRequest().body(errorResponse);
	}

	@ExceptionHandler(ValidationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponse> handleValidationException(ValidationException ex) {
		ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
		return ResponseEntity.badRequest().body(errorResponse);
	}

	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> login(
			@RequestBody AuthenticationRequest request
	) {

		return ResponseEntity.ok(authenticationService.authenticate(request));
    }

	@PreAuthorize("hasAnyAuthority('ADMIN', 'DIPENDENTE', 'MANUTENTORE')")
	@GetMapping("/pageableUtente")
	public List<UtenteDto> getPageableUtente(@RequestParam(defaultValue = "0") int page,
												 @RequestParam(defaultValue = "5") int size) {
		Page<Utente> utentePage = ut.findAllPaginated(page, size);

		return utentePage.getContent().stream()
				.map(utente -> modelMapper.map(utente, UtenteDto.class))
				.collect(Collectors.toList());
	}

	@PreAuthorize("hasAnyAuthority('ADMIN', 'DIPENDENTE', 'MANUTENTORE')")
	@PostMapping("/utenteByRole")
	public ResponseEntity<?> getUtenteByRole(@RequestBody UtenteDto request) {
		String ruolo = request.getRuolo();
		if (ruolo == null || ruolo.isEmpty()) {
			return ResponseEntity.badRequest().body("Devi specificare un ruolo.");
		}

		Page<Utente> utentePage = ut.findByRolePaginated(ruolo, request.getPage(), request.getSize());
		if (utentePage.isEmpty()) {
			return ResponseEntity.badRequest().body("Ops, nessun utente trovato.");
		}

		List<UtenteDto> utenteDtoList = utentePage.getContent().stream()
				.map(utente -> modelMapper.map(utente, UtenteDto.class))
				.toList();

		return ResponseEntity.ok().body(utenteDtoList);
	}
}
