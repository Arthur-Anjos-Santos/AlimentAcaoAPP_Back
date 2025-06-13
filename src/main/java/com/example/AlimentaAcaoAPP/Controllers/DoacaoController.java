package com.example.AlimentaAcaoAPP.Controllers;

import com.example.AlimentaAcaoAPP.Entities.DTOs.DoacaoDTO;
import com.example.AlimentaAcaoAPP.Services.DoacaoService;
import com.example.AlimentaAcaoAPP.Services.UsuarioService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

@RestController
@RequestMapping("/doacao")
public class DoacaoController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private DoacaoService doacaoService;

    @PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
    @PostMapping("/realizar-doacao")
    public ResponseEntity<String> realizarDoacao(
        @Valid @RequestBody DoacaoDTO doacaoDTO,
        Principal principal
    ) {
        String email = principal.getName();
        Integer usuarioId = usuarioService.findByIdUsuario(email);
        doacaoService.registrarDoacao(doacaoDTO, usuarioId);
        return ResponseEntity.ok("Doação realizada com sucesso.");
    }
}
