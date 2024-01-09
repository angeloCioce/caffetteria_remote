package com.example.caffetteria.controller;

import com.example.caffetteria.model.Cliente;
import com.example.caffetteria.model.Ordine;
import com.example.caffetteria.service.ClienteService;
import com.example.caffetteria.service.OrdineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ordine")
public class OrdineController {

	@Autowired
	private OrdineService ord;
	
	@GetMapping("/OrdineList")
	public List<Ordine> getOrdine()
	{
		return ord.findAll();
	}
	
	@GetMapping("/getOrdine/{id_ordine}")
	public Optional<Ordine> findOrdineById(@PathVariable("id_ordine") Long id)
	{
		return ord.findById(id);
	}
	
	@PostMapping("/addOrdine")
	public void saveNewOrdine(Ordine ordine)
	{
		ord.save(ordine);
	}
	
	@DeleteMapping("/deleteOrdine/{id_ordine}")
	public void deleteOrdineById(@PathVariable("id_ordine") Long id)
	{
		ord.delete(id);
	}

}
