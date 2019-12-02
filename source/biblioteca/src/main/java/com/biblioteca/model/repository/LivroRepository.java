package com.biblioteca.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.biblioteca.model.entity.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{
	
	/**@Query("FROM Livro livro WHERE (livro.titulo LIKE '%' || :titulo || '%' OR :titulo IS NULL")
	public Page<Livro> findByFilters(@Param("titulo") String titulo, Pageable pageable);**/
	
}
