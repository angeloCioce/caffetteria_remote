package com.example.caffetteria.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
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
    @JoinColumn(name = "id_utente")
    private Utente utente;
    
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
    
    @OneToMany(mappedBy = "ordine", cascade = CascadeType.ALL)
    private Set<Prodotti_Ordini> prodottiOrdini = new HashSet<>();

//	public Ordine(Long id_ordine, LocalDateTime data_ordine, Double prezzo_totale, Prodotti_Ordini...prodottiOrdini) {
//		this.id_ordine = id_ordine;
//		this.data_ordine = data_ordine;
//		this.prezzo_totale = prezzo_totale;
//		for(Prodotti_Ordini prodottoOrdine : prodottiOrdini) prodottoOrdine.setOrdine(this);
//		this.prodottiOrdini = Stream.of(prodottiOrdini).collect(Collectors.toSet());
//	}
//    
    
    
    
//    
//    @ManyToMany(cascade = { CascadeType.ALL })
//    @JoinTable(
//            name = "prodotti_ordini",
//            joinColumns =  @JoinColumn(name = "id_ordine"),
//            inverseJoinColumns = @JoinColumn(name = "id_prodotto") )
//    Set<Prodotto> prodotto_ordinato;
}
