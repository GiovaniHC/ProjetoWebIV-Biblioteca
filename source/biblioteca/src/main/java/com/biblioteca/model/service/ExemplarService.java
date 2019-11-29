package com.biblioteca.model.service;

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
	
	public Exemplar cadastrarExemplar(Exemplar exemplar) {
		return this.exemplarRepository.save(exemplar);
	}
	
	public void removerExemplar(long id) {
		this.exemplarRepository.deleteById(id);
	}
	
	/** verificar se é necessario um list para saber os exemplares;
	 * ou algo para buscar os IDs
	 */
}
