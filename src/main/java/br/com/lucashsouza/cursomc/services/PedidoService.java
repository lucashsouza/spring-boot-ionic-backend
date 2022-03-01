package br.com.lucashsouza.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lucashsouza.cursomc.domain.Pedido;
import br.com.lucashsouza.cursomc.repositories.PedidoRepository;
import br.com.lucashsouza.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;
	
	public Pedido find(Integer id) {
		
		Optional<Pedido> categoria = repository.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! " +
			      "ID: " + id + " " +
			      "Tipo: " + Pedido.class.getName()));
	}
	
}
