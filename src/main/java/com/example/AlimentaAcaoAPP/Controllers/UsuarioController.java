package com.example.AlimentaAcaoAPP.Controllers;

import com.example.AlimentaAcaoAPP.Entities.DTOs.PessoaDTO;
import com.example.AlimentaAcaoAPP.Entities.DTOs.RendaDTO;
import com.example.AlimentaAcaoAPP.Entities.DTOs.UsuarioDTO;
import com.example.AlimentaAcaoAPP.Services.UsuarioService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PreAuthorize("hasAnyRole('ADMIN','BENEFICIARIO')")
    @GetMapping
    public ResponseEntity<List<PessoaDTO>> listaTodosUsuarios(){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.listaUsuarios());
    }



    @PostMapping
    public ResponseEntity<String> criaUsuario(@Valid @RequestBody PessoaDTO usuario) {
        service.criaUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário criado com sucesso!");
    }

    @PreAuthorize("hasAnyRole('ADMIN','BENEFICIARIO')")
    @PutMapping(value = "/{id}")
    public  ResponseEntity<String> atualizarenda(@Valid @RequestBody RendaDTO rendaDTO, @PathVariable Integer id) {
        service.atualizaRenda(id,rendaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Renda alterada!");
    }

}

