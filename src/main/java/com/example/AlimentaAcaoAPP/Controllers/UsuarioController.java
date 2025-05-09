package com.example.AlimentaAcaoAPP.Controllers;

import com.example.AlimentaAcaoAPP.Entities.DTOs.UsuarioDTO;
import com.example.AlimentaAcaoAPP.Services.UsuarioService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
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
        return ResponseEntity.ok("Hello Arthur!");
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listaTodosUsuarios(){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.listaUsuarios());
    }

    @PostMapping
    public ResponseEntity<String> criaUsuario(@Valid @RequestBody UsuarioDTO usuario){
        service.criaUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário criado com sucesso!");
    }
}
