package com.biblioteca.model.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	@Column(nullable = false, length = 100, unique = true)
	@Size(max = 100)
	private String email;
	
	@Column(nullable = true, length = 20, unique = true)
	private String celular;
	
	@Column(nullable = false)
	private Boolean bibliotecario;
	
	@NotBlank
	@Column(nullable = false, length = 100)
	@Size(max = 100)
	private String senha;
	
	@Column(unique = true, nullable = false, length = 11)
	@NotBlank
	private String cpf;
	
	@Column(nullable = false)
	private Boolean ativo;
	
	
	
	

}
