package com.biblioteca.model.entity;

import java.io.Serializable;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Emprestimo extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private Usuario bibliotecario;
	
	private Usuario leitor;
	
	private LocalTime dataEmprestimo;
	
	private LocalTime dataPrevistaDevolucao;
	
	private LocalTime dataDevolucao;
	
	@OneToOne(targetEntity = Reserva.class, fetch = FetchType.LAZY)
	private Reserva reserva;
}