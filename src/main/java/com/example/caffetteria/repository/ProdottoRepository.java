package com.example.caffetteria.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.caffetteria.model.Prodotto;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Long>{
    Page<Prodotto> findByTipologia(String tipologia, Pageable pageable);
}
