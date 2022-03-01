package br.com.lucashsouza.cursomc.services;

import br.com.lucashsouza.cursomc.domain.Categoria;
import br.com.lucashsouza.cursomc.dto.CategoriaDTO;
import br.com.lucashsouza.cursomc.repositories.CategoriaRepository;
import br.com.lucashsouza.cursomc.services.exceptions.DataIntegrityException;
import br.com.lucashsouza.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	public Categoria find(Integer id) {
		Optional<Categoria> categoria = repository.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! " +
			      "ID: " + id + " " +
			      "Tipo: " + Categoria.class.getName()));
	}

	public List<Categoria> findAll() {
		return repository.findAll();
	}

	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}
	
	public Categoria insert(Categoria categoria) {
		categoria.setId(null);
		return repository.save(categoria);
	}

	public Categoria update(Categoria obj) {
		Categoria newObj = find(obj.getId());
		updateDate(newObj, obj);
		return repository.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos.");
		}
	}

	private void updateDate(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
	}

	public Categoria fromDTO(CategoriaDTO objDTO) {
		return new Categoria(objDTO.getId(), objDTO.getNome());
	}

}
