package com.biblioteca.application.restful;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.model.entity.Livro;
import com.biblioteca.model.service.LivroService;

@Component
@RestController
@RequestMapping( "/api/livro" )
public class LivroResource {

	@Autowired
	private LivroService livroService;
	
	@GetMapping("/list")
	public List<Livro> listar() {
		return this.livroService.listarLivros();
	}
	
	@PostMapping( "/insert" )
	public Livro cadastrar( @RequestBody Livro livro )
	{
		return this.livroService.cadastrarLivro(livro);
	}
	
	@PostMapping( "/update" )
	public Livro atualizar( @RequestBody Livro livro )
	{
		return this.livroService.atualizarLivro(livro);
	}

	@GetMapping("/find")
	public Livro detalhar(@RequestParam("id") Long id) {
		return this.livroService.detalharLivro(id);
	}
	
	@GetMapping("/remove")
	public void remover(@RequestParam("id") Long id) {
		this.livroService.indisponibilizarLivro(id);
	}
}