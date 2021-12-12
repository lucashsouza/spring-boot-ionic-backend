package br.com.lucashsouza.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lucashsouza.cursomc.domain.Cliente;
import br.com.lucashsouza.cursomc.repositories.ClienteRepository;
import br.com.lucashsouza.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	public Cliente buscar(Integer id) {
		
		Optional<Cliente> cliente = repository.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! " +
			      "ID: " + id + " " +
			      "Tipo: " + Cliente.class.getName()));
	}
}
