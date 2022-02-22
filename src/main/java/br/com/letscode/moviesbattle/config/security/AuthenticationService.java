package br.com.letscode.moviesbattle.config.security;

import br.com.letscode.moviesbattle.model.Jogador;
import br.com.letscode.moviesbattle.repository.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private JogadorRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Jogador> jogador = repository.findByEmail(email);
        if (jogador.isPresent()) {
            return jogador.get();
        }
        throw new UsernameNotFoundException("Dados inválidos");
    }

}
