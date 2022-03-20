package br.com.lucashsouza.cursomc.services;

import br.com.lucashsouza.cursomc.domain.Cidade;
import br.com.lucashsouza.cursomc.repositories.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository repository;

    public List<Cidade> findByEstado(Integer estadoId) {
        return repository.findCidades(estadoId);
    }
}
