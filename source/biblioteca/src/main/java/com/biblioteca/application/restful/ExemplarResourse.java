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

import com.biblioteca.model.entity.Exemplar;
import com.biblioteca.model.service.ExemplarService;

@Component
@RestController
@RequestMapping( "/api/exemplar" )
public class ExemplarResourse {

	@Autowired
	private ExemplarService exemplarService;
	
	@GetMapping("/list")
	public List<Exemplar> listar() {
		return this.exemplarService.listarExemplares();
	}
	
	@PostMapping( "/insert" )
	public Exemplar cadastrar( @RequestBody Exemplar exemplar )
	{
		return this.exemplarService.cadastrarExemplar(exemplar);
	}
	
	@PostMapping( "/update" )
	public Exemplar atualizar( @RequestBody Exemplar exemplar )
	{
		return this.exemplarService.atualizarExemplar(exemplar);
	}

	@GetMapping("/find")
	public Exemplar detalhar(@RequestParam("id") Long id) {
		return this.exemplarService.detalharExemplar(id);
	}
	
}