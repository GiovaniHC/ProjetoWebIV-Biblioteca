package com.biblioteca.model.entity;

import java.io.Serializable;
import javax.persistence.Entity;
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
	
	private Boolean disponivel;

	private Livro livro;

}
