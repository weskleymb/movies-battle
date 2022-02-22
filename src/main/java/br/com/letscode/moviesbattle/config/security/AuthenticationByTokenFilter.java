package br.com.letscode.moviesbattle.config.security;

import br.com.letscode.moviesbattle.model.Jogador;
import br.com.letscode.moviesbattle.repository.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationByTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private JogadorRepository jogadorRepository;

    public AuthenticationByTokenFilter(TokenService tokenService, JogadorRepository jogadorRepository) {
        this.tokenService = tokenService;
        this.jogadorRepository = jogadorRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = recuperarToken(request);

        boolean valido = tokenService.isTokenValido(token);
        if (valido) {
            autenticarCliente(token);
        }

        filterChain.doFilter(request, response);
    }

    private void autenticarCliente(String token) {
        Long idJogador = tokenService.getIdJogador(token);
        Jogador jogador = jogadorRepository.findById(idJogador).get();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(jogador, null, jogador.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }
        return token.substring(7, token.length());
    }

}
