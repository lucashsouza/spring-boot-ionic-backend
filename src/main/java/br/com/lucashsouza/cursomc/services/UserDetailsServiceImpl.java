package br.com.lucashsouza.cursomc.services;

import br.com.lucashsouza.cursomc.domain.Cliente;
import br.com.lucashsouza.cursomc.repositories.ClienteRepository;
import br.com.lucashsouza.cursomc.security.UserSpringSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cliente cliente = clienteRepository.findByEmail(email);

        if (cliente == null) {
            throw new UsernameNotFoundException(email);
        }

        return new UserSpringSecurity(cliente.getId(), cliente.getEmail(), cliente.getSenha(), cliente.getPerfis());
    }
}
