package com.biblioteca.model.service;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.model.entity.Emprestimo;
import com.biblioteca.model.entity.Exemplar;
import com.biblioteca.model.entity.ExemplarEnum;
import com.biblioteca.model.repository.EmprestimoRepository;

@Service
@Transactional
public class EmprestimoService {

	@Autowired
	private EmprestimoRepository emprestimoRepository;
	
	public Emprestimo relizarEmprestimo(Emprestimo emprestimo) {
		emprestimo.setDataEmprestimo(LocalDateTime.now());
		emprestimo.setDataPrevistaDevolucao(LocalDateTime.now().plusDays(14));
		return this.emprestimoRepository.save(emprestimo);
	}
	
	public Emprestimo atualizarEmprestimo(Emprestimo emprestimo) {
		return this.emprestimoRepository.save(emprestimo);
	}
	
	public Emprestimo registrarDevolucao(Emprestimo emprestimo) {
		for (Exemplar exemplar : emprestimo.getReserva().getLivro().getExemplares()) {
			if (exemplar.getStatus() == ExemplarEnum.EMPRESTADO) {
				exemplar.setStatus(ExemplarEnum.DISPONIVEL);
			}
		}
		emprestimo.setDataDevolucao(LocalDateTime.now());
		return this.emprestimoRepository.save(emprestimo);
	}
}
