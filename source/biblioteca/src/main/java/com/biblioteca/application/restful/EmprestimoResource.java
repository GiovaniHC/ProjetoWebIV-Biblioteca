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

import com.biblioteca.model.entity.Emprestimo;
import com.biblioteca.model.service.EmprestimoService;

@Component
@RestController
@RequestMapping( "/api/emprestimo" )
public class EmprestimoResource {

	@Autowired
	private EmprestimoService emprestimoService;
	
	@GetMapping("/list")
	public List<Emprestimo> listar() {
		return this.emprestimoService.listarEmprestimos();
	}
	
	@PostMapping( "/insert" )
	public Emprestimo cadastrar( @RequestBody Emprestimo emprestimo )
	{
		return this.emprestimoService.realizarEmprestimo(emprestimo);
	}
	
	@PostMapping( "/update" )
	public Emprestimo atualizar( @RequestBody Emprestimo emprestimo )
	{
		return this.emprestimoService.atualizarEmprestimo(emprestimo);
	}

	@GetMapping("/find")
	public Emprestimo detalhar(@RequestParam("id") Long id) {
		return this.emprestimoService.detalharEmprestimo(id);
	}
	
	@PostMapping("/remove")
	public void remover(@RequestBody Emprestimo emprestimo) {
		this.emprestimoService.registrarDevolucao(emprestimo);
	}
}
