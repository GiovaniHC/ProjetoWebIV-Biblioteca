package com.biblioteca.model.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import com.biblioteca.model.entity.Exemplar;
import com.biblioteca.model.entity.ExemplarEnum;
import com.biblioteca.model.entity.Livro;
import com.biblioteca.model.repository.LivroRepository;

public class ExemplarTests extends AbstractIntegrationTests {

	@Autowired
	private ExemplarService exemplarService;
	
	@Autowired
	private LivroRepository livroRepository;
	
/**TESTES MUST PASS**/
	
	/**teste cadastro de um exemplar**/
	@Test
	@Sql( { "/dataset/truncate.sql", "/dataset/livros.sql", "/dataset/exemplares.sql" })
	public void cadastrarNovoExemplarMustPass()
	{
		Exemplar exemplar = new Exemplar();
		exemplar.setStatus(ExemplarEnum.DISPONIVEL);
		Livro livro = this.livroRepository.findById(1001L).orElse(null);
		exemplar.setLivro(livro);
		exemplarService.cadastrarExemplar(exemplar);

		Assert.assertNotNull(exemplar.getId());

	}
	
	/**teste detalhar um exemplar**/
	@Test
	@Sql( { "/dataset/truncate.sql", "/dataset/livros.sql", "/dataset/exemplares.sql" })
	public void detalharExemplarMustPass()
	{
		Exemplar exemplar = new Exemplar();
		exemplar = exemplarService.detalharExemplar(1003);
		Assert.assertNotNull( exemplar );
		Assert.assertNotNull(exemplar.getId());

	}
	
	/**teste atualizar um exemplar**/
	@Test
	@Sql( { "/dataset/truncate.sql", "/dataset/livros.sql", "/dataset/exemplares.sql" })
	public void atualizarExemplarMustPass()
	{
		Exemplar exemplar = new Exemplar();
		exemplar = exemplarService.detalharExemplar(1003);
		exemplar.setStatus(ExemplarEnum.DISPONIVEL);
		exemplarService.atualizarExemplar(exemplar);
		Assert.assertNotNull( exemplar );
		Assert.assertNotNull(exemplar.getId());

	}
	
	/**teste listar todos os exemplares**/
	@Test
	@Sql( { "/dataset/truncate.sql", "/dataset/livros.sql", "/dataset/exemplares.sql" })
	public void listarTodosExemplarMustPass()
	{
		List<Exemplar> exemplares = exemplarService.listarExemplares();
		Assert.assertNotNull( exemplares );
		Assert.assertTrue(exemplares.size()==12);

	}
}
