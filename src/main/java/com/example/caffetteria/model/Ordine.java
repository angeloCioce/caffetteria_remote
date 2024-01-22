package com.example.caffetteria.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "ordine")
public class Ordine {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_ordine")
    private Long id_ordine;
	
    private LocalDateTime data_ordine;
    private Double prezzo_totale;
    
    @ManyToOne
    @JoinColumn(name = "id_utente", referencedColumnName = "id_utente")
    private Utente utente;
    
    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    private Cliente cliente;
    
    @OneToMany(mappedBy = "ordine", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Prodotti_Ordini> prodottiOrdini = new HashSet<>();
}
