package com.biblioteca.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.model.entity.Exemplar;

public interface ExemplarRepository extends JpaRepository<Exemplar, Long>{

}
