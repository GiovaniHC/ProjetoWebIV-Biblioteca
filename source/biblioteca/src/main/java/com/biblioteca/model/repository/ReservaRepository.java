package com.biblioteca.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.model.entity.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long>{

}
