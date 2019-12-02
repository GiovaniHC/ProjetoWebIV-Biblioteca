package com.biblioteca.model.service;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.biblioteca.model.entity.Exemplar;
import com.biblioteca.model.entity.ExemplarEnum;
import com.biblioteca.model.entity.Livro;
import com.biblioteca.model.repository.LivroRepository;

@Service
@Transactional
public class LivroService {

	@Autowired
	private LivroRepository livroRepository;

	/**
	 * toda vez ao chamar a classe LivroService é verificado se o Livro esta
	 * disponivel
	 **/
	public void init() {
		alteraStatusLivro();
	}

	public Livro cadastrarLivro(Livro livro) {
		return this.livroRepository.save(livro);
	}

	public Livro atualizarLivro(Livro livro) {
		return this.livroRepository.save(livro);
	}

	/** o livro se torna indisponivel **/
	public void indisponibilizarLivro(long id) {
		Livro livro = this.livroRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Nenhum livro encontrado."));
		livro.setDisponivel(false);
		this.livroRepository.save(livro);
	}

	public void disponibilizarLivro(long id) {
		Livro livro = this.livroRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Nenhum livro encontrado."));
		livro.setDisponivel(true);
		this.livroRepository.save(livro);
	}

	public List<Livro> listarLivros() {
		return this.livroRepository.findAll();
	}
	
	public Livro detalharLivro(long id) {
		return this.livroRepository.findById(id).orElse(null);
	}

	/**public Page<Livro> listarLivrosPorTitulo(String titulo, PageRequest pageable) {
		return this.livroRepository.findByFilters(titulo, pageable);
	}**/

	/**
	 * retorna a quantidade total de exemplares independente do status
	 **/
	public int quantidadeExemplar(Livro livro) {
		int qtd = 0;
		qtd = livro.getExemplares().size();
		return qtd;
	}

	/** retorna a quantidade de exemplares disponiveis **/
	public int quantidadeExemplarDisponivel(Livro livro) {
		int qtd = 0;
		for (Exemplar e : livro.getExemplares()) {
			if (e.getStatus() == ExemplarEnum.DISPONIVEL) {
				qtd++;
			}
		}
		return qtd;
	}

	/** retorna a quantidade de exemplares emprestados **/
	public int quantidadeExemplarEmprestado(Livro livro) {
		int qtd = 0;
		for (Exemplar e : livro.getExemplares()) {
			if (e.getStatus() == ExemplarEnum.EMPRESTADO) {
				qtd++;
			}
		}
		return qtd;
	}

	/** retorna a quantidade de exemplares reservados **/
	public int quantidadeExemplarReservado(Livro livro) {
		int qtd = 0;
		for (Exemplar e : livro.getExemplares()) {
			if (e.getStatus() == ExemplarEnum.RESERVADO) {
				qtd++;
			}
		}
		return qtd;
	}

	/**
	 * altera o status do livro de acordo com a quantidade de exemplares disponiveis
	 **/
	public void alteraStatusLivro() {
		for (Livro l : this.livroRepository.findAll()) {
			if (quantidadeExemplarDisponivel(l) > 0) {
				l.setDisponivel(true);
			} else {
				l.setDisponivel(false);
			}
			this.livroRepository.save(l);
		}
	}
}
