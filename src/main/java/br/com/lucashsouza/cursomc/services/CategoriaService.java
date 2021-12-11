package br.com.lucashsouza.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lucashsouza.cursomc.domain.Categoria;
import br.com.lucashsouza.cursomc.repositories.CategoriaRepository;
import br.com.lucashsouza.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;
	
	public Categoria buscar(Integer id) {
		
		Categoria categoria = repository.findById(id).orElse(null);
		
		if (categoria == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! " +
										      "ID: " + id +
										      "Tipo: " + Categoria.class.getName());
		}
		
		return categoria;
	}
	
}
