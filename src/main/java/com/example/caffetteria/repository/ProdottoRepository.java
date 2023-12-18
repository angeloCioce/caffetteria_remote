package com.example.caffetteria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.caffetteria.model.Prodotto;

public interface ProdottoRepository extends JpaRepository<Prodotto, Long>{

}
