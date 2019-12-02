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

import com.biblioteca.model.entity.Reserva;
import com.biblioteca.model.service.ReservaService;

@Component
@RestController
@RequestMapping( "/api/reserva" )
public class ReservaResource {

	@Autowired
	private ReservaService reservaService;
	
	@GetMapping("/list")
	public List<Reserva> listar() {
		return this.reservaService.listarReservas();
	}
	
	@PostMapping( "/insert" )
	public Reserva cadastrar( @RequestBody Reserva reserva )
	{
		return this.reservaService.novaReserva(reserva);
	}
	
	@PostMapping( "/update" )
	public Reserva atualizar( @RequestBody Reserva reserva )
	{
		return this.reservaService.atualizarReserva(reserva);
	}

	@GetMapping("/find")
	public Reserva detalhar(@RequestParam("id") Long id) {
		return this.reservaService.detalharReserva(id);
	}
	
	@PostMapping("/remove")
	public void remover(@RequestBody Reserva reserva) {
		this.reservaService.cancelarReserva(reserva);
	}
}