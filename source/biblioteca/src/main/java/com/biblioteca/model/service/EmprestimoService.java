package com.biblioteca.model.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.model.entity.Emprestimo;
import com.biblioteca.model.repository.EmprestimoRepository;

@Service
@Transactional
public class EmprestimoService {

	@Autowired
	private EmprestimoRepository emprestimoRepository;
	
	@Autowired
	private ExemplarService exemplarService;
	
	/**realizar um novo emprestimo**/
	public Emprestimo realizarEmprestimo(Emprestimo emprestimo) {
		emprestimo.setDataEmprestimo(LocalDateTime.now());
		emprestimo.setDataPrevistaDevolucao(LocalDateTime.now().plusDays(14));
		this.exemplarService.emprestarExemplares(emprestimo.getReserva().getLivro(), emprestimo.getReserva().getQuantidadeExemplar());
		return this.emprestimoRepository.save(emprestimo);
	}
	
	/**alterar um emprestimo**/
	public Emprestimo atualizarEmprestimo(Emprestimo emprestimo) {
		return this.emprestimoRepository.save(emprestimo);
	}
	
	/**registrar a devolução do livro(exemplares)**/
	public Emprestimo registrarDevolucao(Emprestimo emprestimo) {
		this.exemplarService.devolucaoExemplares(emprestimo.getReserva().getLivro(), emprestimo.getReserva().getQuantidadeExemplar());
		emprestimo.setDataDevolucao(LocalDateTime.now());
		return this.emprestimoRepository.save(emprestimo);
	}
	
	/**listagem de todos os emprestimos**/
	public List<Emprestimo> listarEmprestimos() {
		return this.emprestimoRepository.findAll();
	}
	
	/**detalhamento de um emprestimo**/
	public Emprestimo detalharEmprestimo(long id) {
		return this.emprestimoRepository.findById(id).orElse(null);
	}
	
}
