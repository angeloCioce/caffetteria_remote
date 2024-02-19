package com.example.caffetteria.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.caffetteria.model.Ordine;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface OrdineRepository extends JpaRepository<Ordine, Long>{
    @Query("SELECT o FROM Ordine o WHERE o.data_ordine BETWEEN ?1 AND ?2")
    Page<Ordine> findByDataOrdineBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
}
