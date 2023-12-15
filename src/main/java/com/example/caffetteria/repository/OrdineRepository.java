package com.example.caffetteria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.caffetteria.model.Ordine;

public interface OrdineRepository extends JpaRepository<Ordine, Long>{

}
