package com.biblioteca.model.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ValidationException;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;

import com.biblioteca.model.entity.Exemplar;
import com.biblioteca.model.entity.ExemplarEnum;
import com.biblioteca.model.entity.Livro;

public class LivroTests extends AbstractIntegrationTests {

	@Autowired
	private LivroService livroService;
	
	/**TESTES MUST PASS**/
	
	/**teste cadastro de um livro**/
	@Test
	@Sql( { "/dataset/truncate.sql", "/dataset/livros.sql" })
	public void cadastrarNovoLivroMustPass()
	{
		Livro livro = new Livro();
		livro.setAutor("Autor de Teste");
		livro.setDisponivel(true);
		livro.setEdicao("12");
		livro.setEditora("Editora de Teste");
		livro.setIsbn("1010101010101");
		livro.setTitulo("Teste Testante");
		this.livroService.cadastrarLivro(livro);
		Assert.assertNotNull( livro );
		Assert.assertNotNull(livro.getId());

	}
	
	/**teste cadastro de exemplares no livro**/
	@Test
	@Sql( { "/dataset/truncate.sql", "/dataset/livros.sql" })
	public void cadastrarLivroMustPassInserirExemplares()
	{
		Livro livro = new Livro();
		livro.setAutor("José de Anchieta");
		livro.setCreated(LocalDateTime.now());
		livro.setDisponivel(true);
		livro.setEdicao("1");
		livro.setEditora("Ler");
		livro.setIsbn("3697898647895");
		livro.setTitulo("Benefícios do Café");
		
		Exemplar exem1 = new Exemplar();
		exem1.setLivro(livro);
		exem1.setStatus(ExemplarEnum.DISPONIVEL);
		
		livro.getExemplares().add(exem1);
		
		Exemplar exem2 = new Exemplar();
		exem2.setLivro(livro);
		exem2.setStatus(ExemplarEnum.DISPONIVEL);
		
		livro.getExemplares().add(exem2);
		
		Exemplar exem3 = new Exemplar();
		exem3.setLivro(livro);
		exem3.setStatus(ExemplarEnum.DISPONIVEL);
		
		livro.getExemplares().add(exem3);
		
		Exemplar exem4 = new Exemplar();
		exem4.setLivro(livro);
		exem4.setStatus(ExemplarEnum.DISPONIVEL);
		
		livro.getExemplares().add(exem4);
		
		livroService.cadastrarLivro(livro);
		
		Assert.assertNotNull( livro.getId() );
		Assert.assertTrue(livro.getExemplares().size() == 4);

		/**for(Exemplar exem : livro.getExemplares()) {
			Assert.assertNotNull(exem.getId());
		}**/
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
		livro = this.livroService.detalharLivro(1001);
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
	
	/**teste cadastro de exemplares no livro**/
	@Test
	@Sql( { "/dataset/truncate.sql", "/dataset/livros.sql" })
	public void atualizarLivroMustPassInserirExemplares()
	{
		Livro livro = new Livro();
		livro = livroService.detalharLivro(1002);
		
		Exemplar exem1 = new Exemplar();
		exem1.setLivro(livro);
		exem1.setStatus(ExemplarEnum.DISPONIVEL);
		exem1.setCreated(LocalDateTime.now());
		
		livro.getExemplares().add(exem1);
		
		Exemplar exem2 = new Exemplar();
		exem2.setLivro(livro);
		exem2.setStatus(ExemplarEnum.DISPONIVEL);
		exem2.setCreated(LocalDateTime.now());
		
		livro.getExemplares().add(exem2);
		
		livroService.atualizarLivro(livro);
		
		Assert.assertNotNull( livro.getId() );
		Assert.assertTrue(livro.getExemplares().size() == 2);
		
	}
	
	
	
	/**teste para listar todos os livros**/
	@Test
	@Sql( {		    
	      "/dataset/truncate.sql",
				"/dataset/livros.sql"
		})
	public void listarLivrosMustPass()
	{
		List<Livro> livros = new ArrayList<Livro>();
		livros = livroService.listarLivros();
		Assert.assertNotNull( livros );
		Assert.assertTrue(livros.size()==3);

	}
	
	/**teste para listar livros pelo titulo**/
	@Test
	@Sql( {		    
	      "/dataset/truncate.sql",
				"/dataset/livros.sql"
		})
	public void listarLivrosPorTituloMustPass()
	{
		List<Livro> livros = livroService.listarLivrosPorTitulo("Raspberry Pi", null).getContent();
		Assert.assertNotNull( livros );
		Assert.assertEquals(1, livros.size());

	}
	
	/**teste para detalhar um livro**/
	@Test
	@Sql( {		    
	      "/dataset/truncate.sql",
				"/dataset/livros.sql"
		})
	public void detalharLivroMustPass()
	{
		Livro livro = new Livro();
		livro = livroService.detalharLivro(1001);
		Assert.assertNotNull( livro );
		Assert.assertNotNull( livro.getId() );
	}
	
	/**teste para retornar a quantidade total de exemplares do livro disponiveis**/
	@Test
	@Sql( {		    
	      "/dataset/truncate.sql",
				"/dataset/livros.sql", "/dataset/exemplares.sql"
		})
	public void quantidadeTotalExemplaresDoLivroMustPass()
	{
		int qtd = livroService.quantidadeExemplar(livroService.detalharLivro(1001));
		Assert.assertTrue( qtd == 4 );
	}
	
	/**teste para retornar a quantidade de exemplares do livro disponiveis**/
	@Test
	@Sql( {		    
	      "/dataset/truncate.sql",
				"/dataset/livros.sql", "/dataset/exemplares.sql"
		})
	public void quantidadeExemplaresDoLivroReservadosMustPass()
	{
		int qtd = livroService.quantidadeExemplarReservado(livroService.detalharLivro(1002));
		Assert.assertTrue( qtd == 2 );
	}
	
	/**TESTES MUST FAIL**/
	
	/**teste para verificar se realmente não é possivel ter um novo livro com um ISBN já cadastrado**/
	@Test(expected = DataIntegrityViolationException.class)
	@Sql( { "/dataset/truncate.sql", "/dataset/livros.sql" })
	public void cadastrarLivroMustFailIsbnJaCadastrado()
	{
		Livro livro = new Livro();
		livro.setAutor("Autor de Teste");
		livro.setDisponivel(true);
		livro.setEdicao("12");
		livro.setEditora("Editora de Teste");
		livro.setIsbn("1234567891234");
		livro.setTitulo("The books on the table");
		this.livroService.cadastrarLivro(livro);

	}
	
	/**teste cadastro de um livro com campos nulos e/ou em branco**/
	@Test(expected = ValidationException.class)
	@Sql( { "/dataset/truncate.sql", "/dataset/livros.sql" })
	public void cadastrarNovoLivroMustFailCamposNulosOuEmBranco ()
	{
		Livro livro = new Livro();
		livro.setAutor("");/**campo em branco**/
		livro.setDisponivel(true);
		livro.setEdicao("12");
		livro.setEditora("Editora de Teste");
		/**livro.setIsbn("1010101010101");**//**nulo**/
		livro.setTitulo("Teste Testante");
		this.livroService.cadastrarLivro(livro);
	}
	
	/**teste para retornar a quantidade de exemplares do livro que não existe disponiveis**/
	@Test
	@Sql( {		    
	      "/dataset/truncate.sql",
				"/dataset/livros.sql", "/dataset/exemplares.sql"
		})
	public void quantidadeExemplaresDoLivroReservadosMustFailNaoExiste()
	{
		int qtd = livroService.quantidadeExemplarReservado(livroService.detalharLivro(2000));
		Assert.assertTrue( qtd == 0);
	}
}
