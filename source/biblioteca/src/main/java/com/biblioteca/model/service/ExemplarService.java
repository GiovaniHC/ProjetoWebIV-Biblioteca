package com.biblioteca.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.model.entity.Exemplar;
import com.biblioteca.model.repository.ExemplarRepository;

@Service
@Transactional
public class ExemplarService {

	@Autowired
	private ExemplarRepository exemplarRepository;

	/**cadastrar um novo exemplar**/
	public Exemplar cadastrarExemplar(Exemplar exemplar) {
		return this.exemplarRepository.save(exemplar);
	}

	/**detalhar um exemplar**/
	public Exemplar detalharExemplar(long id) {
		return this.exemplarRepository.findById(id).orElse(null);
	}

	/**alterar um exemplar**/
	public Exemplar atualizarExemplar(Exemplar exemplar) {
		return this.exemplarRepository.save(exemplar);
	}

	/**listar todos os exemplares**/
	public List<Exemplar> listarExemplares() {
		return this.exemplarRepository.findAll();
	}

}
