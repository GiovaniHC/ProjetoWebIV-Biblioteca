package com.biblioteca.model.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.model.entity.Exemplar;
import com.biblioteca.model.entity.ExemplarEnum;
import com.biblioteca.model.entity.Livro;
import com.biblioteca.model.entity.Reserva;
import com.biblioteca.model.repository.LivroRepository;
import com.biblioteca.model.repository.ReservaRepository;

@Service
@Transactional
public class ReservaService {

	@Autowired
	private ReservaRepository reservaRepository;
	private LivroRepository livroRepository;

	/**toda vez que a classe é acessada**/
	public void init() {
		cancelarReservaPorTempo();
	}

	public List<Reserva> listarReservas(){
		return this.reservaRepository.findAll();
	}
	
	public Reserva detalharReserva(long id) {
		return this.reservaRepository.findById(id).orElse(null);
	}
	
	public Reserva atualizarReserva(Reserva reserva) {
		return this.reservaRepository.save(reserva);
	}
	
	/**altera a quantidade de exemplares reservados na lista de exemplares do livro, 
	 * alterando o status para "RESERVADO", somente se o exemplar esteja com status "DISPONIVEL"**/
	public Reserva novaReserva(Reserva reserva) {
		List<Exemplar> exemplares = new ArrayList<Exemplar>();
			for (Exemplar exemplar : reserva.getExemplares()) {
				if (exemplar.getStatus() == ExemplarEnum.DISPONIVEL) {
					exemplar.setStatus(ExemplarEnum.RESERVADO);
					exemplares.add(exemplar);
				}
			}
		Livro livro = this.livroRepository.findById(reserva.getLivro().getId()).orElse(null);
		livro.setExemplares(exemplares);
		this.livroRepository.save(livro);
		return this.reservaRepository.save(reserva);
	}

	/**altera a quantidade de exemplares reservados na lista de exemplares do livro, 
	 * alterando o status para "DISPONIVEL", somente se o exemplar esteja com status "RESERVADO" e a 
	 * reserva é deletada**/
	public void cancelarReserva(Reserva reserva) {
		List<Exemplar> exemplares = new ArrayList<Exemplar>();
			for (Exemplar exemplar : reserva.getExemplares()) {
				if (exemplar.getStatus() == ExemplarEnum.RESERVADO) {
					exemplar.setStatus(ExemplarEnum.DISPONIVEL);
					exemplares.add(exemplar);
				}
			}
		Livro livro = this.livroRepository.findById(reserva.getLivro().getId()).orElse(null);
		livro.setExemplares(exemplares);
		this.livroRepository.save(livro);
		this.reservaRepository.delete(reserva);
	}

	/**
	 * verificar se passaram 7 dias da data de reserva, se sim os exemplares voltam
	 * a ser disponiveis e deleta a reserva
	 **/
	public void cancelarReservaPorTempo() {
		for (Reserva reserva : this.reservaRepository.findAll()) {
			if (reserva.getDataReserva().isBefore(reserva.getDataReserva().plusDays(7))) {
				cancelarReserva(reserva);
			}
		}
	}

}
