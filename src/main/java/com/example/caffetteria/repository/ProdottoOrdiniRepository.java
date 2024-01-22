package com.example.caffetteria.repository;

import com.example.caffetteria.model.Ordine;
import com.example.caffetteria.model.Prodotti_Ordini;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdottoOrdiniRepository extends JpaRepository<Prodotti_Ordini, Long> {
    List<Prodotti_Ordini> findByOrdine(Ordine ordine);
}
