package com.example.AlimentaAcaoAPP.Services;

import com.example.AlimentaAcaoAPP.Entities.Usuario;
import com.example.AlimentaAcaoAPP.Entities.DTOs.UsuarioDTO;
import com.example.AlimentaAcaoAPP.Repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    // Lista todos os usuários como DTOs
    public List<UsuarioDTO> listaUsuarios() {
        return repository.findAll()
                         .stream()
                         .map(UsuarioDTO::new)
                         .collect(Collectors.toList());
    }

    // Cria um novo usuário a partir de um DTO
    public void criaUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario(usuarioDTO);
        repository.save(usuario);
    }

    // Autentica um usuário usando CPF e senha
    public UsuarioDTO autenticarUsuario(String cpf, String senha) {
        return repository.findByCpfAndSenha(cpf, senha)
                         .map(UsuarioDTO::new)
                         .orElseThrow(() -> new RuntimeException("CPF ou senha inválidos"));
    }
}
