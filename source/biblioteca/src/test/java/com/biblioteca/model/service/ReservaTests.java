package com.biblioteca.model.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import com.biblioteca.model.entity.Exemplar;
import com.biblioteca.model.entity.ExemplarEnum;
import com.biblioteca.model.entity.Livro;
import com.biblioteca.model.entity.Reserva;
import com.biblioteca.model.repository.LivroRepository;
import com.biblioteca.model.repository.ReservaRepository;
import com.biblioteca.model.repository.UsuarioRepository;

public class ReservaTests extends AbstractIntegrationTests {
	
	@Autowired
	private ReservaService reservaService;
	
	@Autowired
	private LivroService livroService;
	
	@Autowired
	private ReservaRepository reservaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private LivroRepository livroRepository;
	
	
/**TESTES MUST PASS**/
	
	/**teste cadastro de uma reserva**/
	@Test
	@Sql( { "/dataset/truncate.sql", "/dataset/usuarios.sql", "/dataset/livros.sql", "/dataset/exemplares.sql" })
	public void cadastrarNovaReservaLivroMustPass()
	{
		Reserva reserva = new Reserva();
		Livro livro = new Livro();
		livro = livroRepository.findById(1002L).orElse(null);
		reserva.setCreated(LocalDateTime.now());
		reserva.setDataReserva(LocalDateTime.now());
		reserva.setLeitor(usuarioRepository.findById(1002L).orElse(null));
		for(Exemplar exemplar : livroService.listaExemplaresDisponiveis(livro)) {
			while(reserva.getExemplares().size() < 2) {
				exemplar.setStatus(ExemplarEnum.RESERVADO);
				reserva.getExemplares().add(exemplar);
			}
		}
		reservaService.atualizarReserva(reserva);
		Assert.assertNotNull( reserva );
		Assert.assertNotNull(reserva.getId());
		
		for(Exemplar exemplar : reserva.getExemplares()) {
			Assert.assertNotNull(exemplar.getId());
			Assert.assertTrue(exemplar.getStatus() == ExemplarEnum.RESERVADO);
		}

	}
}
