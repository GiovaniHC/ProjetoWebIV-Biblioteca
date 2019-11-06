package com.biblioteca.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

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
	private String nome;
	private String email;
	private String celular;
	private Boolean bibliotecario;
	private String senha;
	@Column(unique = true, nullable = false, length = 11)
	@NotBlank
	private String cpf;
	
	@OneToMany(targetEntity=Reserva.class, mappedBy="leitor", orphanRemoval=true, cascade = {CascadeType.ALL}, fetch = FetchType.EAGER) 
	private List<Reserva> reservas = new ArrayList<Reserva>();
	
	
	

}
