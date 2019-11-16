package com.biblioteca.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.model.entity.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{

}
