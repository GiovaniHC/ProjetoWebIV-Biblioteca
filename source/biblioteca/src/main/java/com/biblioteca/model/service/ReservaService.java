package com.biblioteca.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.model.entity.Reserva;
import com.biblioteca.model.repository.ReservaRepository;

@Service
@Transactional
public class ReservaService {

	@Autowired
	private ReservaRepository reservaRepository;
	
	@Autowired
	private ExemplarService exemplarService;

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
		this.exemplarService.reservarExemplares(reserva.getLivro(), reserva.getQuantidadeExemplar());
		return this.reservaRepository.save(reserva);
	}

	/**altera a quantidade de exemplares reservados na lista de exemplares do livro, 
	 * alterando o status para "DISPONIVEL", somente se o exemplar esteja com status "RESERVADO" e a 
	 * reserva é deletada**/
	public void cancelarReserva(Reserva reserva) {
		this.exemplarService.cancelarReservaExemplares(reserva.getLivro(), reserva.getQuantidadeExemplar());
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
