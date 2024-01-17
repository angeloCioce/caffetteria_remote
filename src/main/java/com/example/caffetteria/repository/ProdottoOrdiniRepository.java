package com.example.caffetteria.repository;

import com.example.caffetteria.model.Ordine;
import com.example.caffetteria.model.Prodotti_Ordini;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdottoOrdiniRepository extends JpaRepository<Prodotti_Ordini, Long> {
}
