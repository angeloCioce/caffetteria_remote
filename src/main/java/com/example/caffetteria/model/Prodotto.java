package com.example.caffetteria.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
@Table(name = "prodotto")
public class Prodotto {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_prodotto")
    private Long id_prodotto;
	
    private String nome_prodotto;
    private String descrizione;
    private Double prezzo_ingrosso;
    private Double prezzo_dettaglio;
    private Integer quantita;
    private String tipologia;
    
    @OneToMany(mappedBy = "prodotto", cascade = CascadeType.ALL)
    private Set<Prodotti_Ordini> prodottiOrdini = new HashSet<>();
}
