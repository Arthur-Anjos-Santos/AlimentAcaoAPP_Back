package com.example.AlimentaAcaoAPP.Services;

import com.example.AlimentaAcaoAPP.Entities.Pessoa;
import com.example.AlimentaAcaoAPP.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Pessoa> userOptional = repository.findByEmail(username);
        Pessoa user = userOptional.orElseThrow(() ->
                new UsernameNotFoundException("Usuário não encontrado com o email: " + username)
        );

        return user;
    }
}
