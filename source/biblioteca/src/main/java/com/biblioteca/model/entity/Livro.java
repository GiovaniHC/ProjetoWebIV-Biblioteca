package com.biblioteca.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

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

public class Livro extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotBlank
	private String titulo;

	@NotBlank
	private String editora;

	@NotBlank
	private String autor;

	@NotBlank
	@Column(nullable = false, length = 4)
	private String edicao;

	@Column(nullable = false, length = 13, unique = true)
	private String isbn;

	/**lista de exemplares que pertencem a este livro**/
	@OneToMany(targetEntity = Exemplar.class, fetch = FetchType.EAGER, mappedBy = "livro")
	private List<Exemplar> exemplares = new ArrayList<Exemplar>();
	
	/**lista de reservas que este livro esta**/
	@OneToMany(targetEntity = Reserva.class, fetch = FetchType.LAZY, mappedBy = "livro")
	private List<Reserva> reservas = new ArrayList<Reserva>();

	/**
	 * so servira para informar caso todos os exemplares sejam reservados/emprestados
	 * ou seja alterado manualmente
	 **/
	private Boolean disponivel;

}