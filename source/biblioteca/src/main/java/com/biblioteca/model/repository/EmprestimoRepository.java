package com.biblioteca.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.biblioteca.model.entity.Emprestimo;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long>{
	
}
