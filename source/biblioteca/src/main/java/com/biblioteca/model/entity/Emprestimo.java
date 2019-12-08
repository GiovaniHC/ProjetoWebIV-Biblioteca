package com.biblioteca.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
	
	@NotBlank
	private LocalDateTime dataEmprestimo;
	
	@NotBlank
	private LocalDateTime dataPrevistaDevolucao;
	
	/**é nula enquanto não for realizado a devolução do livro**/
	private LocalDateTime dataDevolucao;
	
	/**cada reserva pode ou não se tornar um emprestimo**/
	@OneToOne(targetEntity = Reserva.class, fetch = FetchType.LAZY, optional = true)
	private Reserva reserva;
	
}