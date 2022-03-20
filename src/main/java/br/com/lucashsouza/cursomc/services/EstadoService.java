package br.com.lucashsouza.cursomc.services;

import br.com.lucashsouza.cursomc.domain.Estado;
import br.com.lucashsouza.cursomc.repositories.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository repository;

    public List<Estado> findAll() {
        return repository.findAllByOrderByNome();
    }
}
