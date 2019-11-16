package com.biblioteca.model.entity;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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

	@OneToMany(targetEntity = Exemplar.class, fetch = FetchType.LAZY)
	private List<Exemplar> exemplares = new ArrayList<Exemplar>();

	private LocalTime dataReserva;

	private Integer quantidade;

}