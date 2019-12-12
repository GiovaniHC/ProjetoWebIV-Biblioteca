package com.biblioteca.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.model.entity.Exemplar;
import com.biblioteca.model.entity.ExemplarEnum;
import com.biblioteca.model.entity.Livro;
import com.biblioteca.model.repository.ExemplarRepository;

@Service
@Transactional
public class ExemplarService {

	@Autowired
	private ExemplarRepository exemplarRepository;

	@Autowired
	private LivroService livroService;

	/** cadastrar um novo exemplar **/
	public Exemplar cadastrarExemplar(Exemplar exemplar) {
		return this.exemplarRepository.save(exemplar);
	}

	/** detalhar um exemplar **/
	public Exemplar detalharExemplar(long id) {
		return this.exemplarRepository.findById(id).orElse(null);
	}

	/** alterar um exemplar **/
	public Exemplar atualizarExemplar(Exemplar exemplar) {
		return this.exemplarRepository.save(exemplar);
	}

	/** listar todos os exemplares **/
	public List<Exemplar> listarExemplares() {
		return this.exemplarRepository.findAll();
	}

	public void reservarExemplares(Livro livro, int quantidade) {
		for (int i = 0; i < quantidade; i++) {
			Exemplar e = livroService.listaExemplaresDisponiveis(livro).get(i);
			e.setStatus(ExemplarEnum.RESERVADO);
			exemplarRepository.save(e);
		}
	}

	public void emprestarExemplares(Livro livro, int quantidade) {
		for (int i = 0; i < quantidade; i++) {
			Exemplar e = livroService.listaExemplaresReservados(livro).get(i);
			e.setStatus(ExemplarEnum.EMPRESTADO);
			exemplarRepository.save(e);
		}
	}

	public void devolucaoExemplares(Livro livro, int quantidade) {
		for (int i = 0; i < quantidade; i++) {
			Exemplar e = livroService.listaExemplaresEmprestados(livro).get(i);
			e.setStatus(ExemplarEnum.DISPONIVEL);
			exemplarRepository.save(e);
		}
	}

	public void cancelarReservaExemplares(Livro livro, int quantidade) {
		for (int i = 0; i < quantidade; i++) {
			Exemplar e = livroService.listaExemplaresReservados(livro).get(i);
			e.setStatus(ExemplarEnum.DISPONIVEL);
			exemplarRepository.save(e);
		}
	}
}
