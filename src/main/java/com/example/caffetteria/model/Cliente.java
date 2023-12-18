package com.example.caffetteria.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cliente")
public class Cliente {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_cliente")
    private Long id_cliente;
	
    private String nome;
    private String cognome;
    
    @OneToMany(mappedBy = "cliente")
    private Set<Ordine> ordine = new HashSet<>(); 
}
