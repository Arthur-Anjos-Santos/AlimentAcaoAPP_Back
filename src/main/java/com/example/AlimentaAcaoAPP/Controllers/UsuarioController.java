package com.example.AlimentaAcaoAPP.Controllers;

import com.example.AlimentaAcaoAPP.Entities.DTOs.LoginDTO;
import com.example.AlimentaAcaoAPP.Entities.DTOs.UsuarioDTO;
import com.example.AlimentaAcaoAPP.Services.UsuarioService;

import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping("/teste")
    public ResponseEntity<String> teste() {
        return ResponseEntity.ok("Olá mundo!");
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listaTodosUsuarios(){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.listaUsuarios());
    }

    @PostMapping
    public ResponseEntity<String> criaUsuario(@Valid @RequestBody UsuarioDTO usuario) {
        service.criaUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário criado com sucesso!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            UsuarioDTO usuario = service.autenticarUsuario(loginDTO.getCpf(), loginDTO.getSenha());
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
