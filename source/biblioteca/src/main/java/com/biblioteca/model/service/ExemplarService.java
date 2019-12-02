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

	public Exemplar cadastrarExemplar(Exemplar exemplar) {
		return this.exemplarRepository.save(exemplar);
	}

	public Exemplar detalharExemplar(long id) {
		return this.exemplarRepository.findById(id).orElse(null);
	}

	public Exemplar atualizarExemplar(Exemplar exemplar) {
		return this.exemplarRepository.save(exemplar);
	}

	public List<Exemplar> listarExemplares() {
		return this.exemplarRepository.findAll();
	}

}
