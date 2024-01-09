package com.example.caffetteria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.caffetteria.model.Ordine;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdineRepository extends JpaRepository<Ordine, Long>{

}
