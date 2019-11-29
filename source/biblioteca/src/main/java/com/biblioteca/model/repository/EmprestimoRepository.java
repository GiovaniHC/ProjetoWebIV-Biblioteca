package com.biblioteca.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.biblioteca.model.entity.Emprestimo;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long>{
	
	/**@Query("FROM Emprestimo emprestimo"
			+ "WHERE (emprestimo.cpf LIKE '%' || :cpf || '%' OR :cpf IS NULL")
	public Page<Emprestimo> findByLeitor(@Param("cpf")String cpf, Pageable pageable);

	@Query("FROM Emprestimo emprestimo"
			+ "WHERE (emprestimo.cpf LIKE '%' || :cpf || '%' OR :cpf IS NULL")
	public Page<Emprestimo> findByBibliotecarioCpf(@Param("cpf")String cpf, Pageable pageable);**/
	
	
	
}
