package com.biblioteca.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
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

public class Reserva extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne(targetEntity = Usuario.class, fetch = FetchType.LAZY, optional = false)
	private Usuario leitor;

	@NotNull
	private Livro livro;
	
	@NotNull
	private LocalDateTime dataReserva;

	@NotNull
	@NotBlank
	private Integer quantidadeExemplar;

}