package com.biblioteca.model.service;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.jdbc.Sql;

import com.biblioteca.model.entity.Livro;
import com.biblioteca.model.entity.RoleEnum;
import com.biblioteca.model.repository.LivroRepository;
import com.biblioteca.model.repository.UsuarioRepository;

public class LivroTests extends AbstractIntegrationTests {
	
	
	@Autowired
	private LivroRepository livroRepository;

	@Autowired
	private LivroService livroService;
	
	
	
	/**teste cadastro livro**/
	@Test
	@Sql( { "/dataset/truncate.sql", "/dataset/livros.sql" })
	public void cadastrarLivroMustPass()
	{
		Livro livro = new Livro();
		livro.setTitulo("Livro de Teste");
		livro.setCreated(LocalDateTime.now());
		livro.setAutor("Autor de Teste");
		livro.setDisponivel(true);
		livro.setEdicao("12");
		livro.setEditora("Editora de Teste");
		livro.setIsbn("1234567891237");
		livro.setTitulo("Teste Testante");
		this.livroService.cadastrarLivro(livro);
		Assert.assertNotNull( livro );
		Assert.assertNotNull(livro.getId());

	}
	
	/**teste de alterar livro**/
	@Test
	@Sql( {		    
	      "/dataset/truncate.sql",
				"/dataset/livros.sql"
		})
	public void alterarLivroMustPass()
	{
		Livro livro = new Livro();
		livro = this.livroService.detalharLivro(5);
		livro.setTitulo("Livro de Teste");
		livro.setCreated(LocalDateTime.now());
		livro.setAutor("Autor de Teste");
		livro.setDisponivel(false);
		livro.setEdicao("3");
		livro.setEditora("Editora de Teste");
		livro.setIsbn("1234567891234");
		livro.setTitulo("Titulo de Teste");
		this.livroService.atualizarLivro(livro);
		Assert.assertNotNull( livro );
		Assert.assertNotNull( livro.getId() );

	}
	
	
	/**teste para verificar se realmente não é possivel ter um novo livro com um ISBN já cadastrado**/
	@Test(expected = DataIntegrityViolationException.class)
	@Sql( { "/dataset/truncate.sql", "/dataset/livros.sql" })
	public void cadastrarLivroMustFailComIsbnCadastrado()
	{
		Livro livro = new Livro();
		livro.setTitulo("Livro de Teste");
		livro.setCreated(LocalDateTime.now());
		livro.setAutor("Autor de Teste");
		livro.setDisponivel(true);
		livro.setEdicao("12");
		livro.setEditora("Editora de Teste");
		livro.setIsbn("1234567891237");
		livro.setTitulo("Teste Testante");
		this.livroService.cadastrarLivro(livro);

	}
	
}
