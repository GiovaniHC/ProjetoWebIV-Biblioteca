package com.biblioteca.model.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
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

public class Exemplar extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Enumerated( EnumType.ORDINAL )
	private ExemplarEnum status;

	@NotNull
	@ManyToOne(targetEntity = Livro.class, fetch = FetchType.LAZY, optional = false)
	private Livro livro;
	
	@ManyToOne(targetEntity = Reserva.class, fetch = FetchType.LAZY, optional = false)
	private Reserva reserva;

}
