package com.biblioteca.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	/*
	 * 
	 */
	Usuario findByEmailIgnoreCase(String email);
	
	/**
	 *
	 */
	Optional<Usuario> findByPasswordResetToken( String token );
	
	/**
	 *
	 */
	Optional<Usuario> findByAccountActivateToken( String token );
	
}
