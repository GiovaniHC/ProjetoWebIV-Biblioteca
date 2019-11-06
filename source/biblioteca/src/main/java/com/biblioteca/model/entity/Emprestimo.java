package com.biblioteca.model.entity;

import java.io.Serializable;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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

public class Emprestimo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	private Usuario bibliotecario;
	private Usuario leitor;
	private LocalTime dataEmprestimo;
	private LocalTime dataPrevistaDevolucao;
	private LocalTime dataDevolucao;
	private Exemplar exemplar;
	

}