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
@Table(name = "utente")
public class Utente {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_utente")
    private Long id_utente;
	
    private String username;
    private String password;
    private String ruolo;
    
    @OneToMany(mappedBy = "utente")
    private Set<Ordine> ordine = new HashSet<>(); 
}
