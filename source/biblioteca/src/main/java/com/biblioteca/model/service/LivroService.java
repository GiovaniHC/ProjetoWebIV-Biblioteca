package com.biblioteca.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.biblioteca.model.entity.Livro;
import com.biblioteca.model.repository.LivroRepository;

@Service
@Transactional
public class LivroService {
	
	@Autowired
	private LivroRepository livroRepository;

	public Livro cadastrarLivro(Livro livro) {
		return this.livroRepository.save(livro);
	}
	
	public Livro atualizarLivro(Livro livro) {
		return this.livroRepository.save(livro);
	}
	
	public void removerLivro(long id) {
		this.livroRepository.deleteById(id);
	}
	
	public List<Livro> listarLivros(){
		return this.livroRepository.findAll();
	}
	
	public Page<Livro> listarLivrosPorTitulo(String titulo, PageRequest pageable){
		return this.livroRepository.findByTitulo(titulo, pageable);
	}
}
