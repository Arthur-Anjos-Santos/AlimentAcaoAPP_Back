package com.example.AlimentaAcaoAPP.Services;

import com.example.AlimentaAcaoAPP.Entities.Usuario;
import com.example.AlimentaAcaoAPP.Entities.DTOs.UsuarioDTO;
import com.example.AlimentaAcaoAPP.Repository.UsuarioRepository;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public List<UsuarioDTO> listaUsuarios(){
        return repository.findAll().stream().map(UsuarioDTO::new).collect(Collectors.toList());
    }

    public void criaUsuario(UsuarioDTO usuarioDTO){
        Usuario usuario = new Usuario(usuarioDTO);
        repository.save(usuario);
    }

}
