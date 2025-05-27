package com.example.AlimentaAcaoAPP.Controllers;

import com.example.AlimentaAcaoAPP.Entities.DTOs.PessoaDTO;
import com.example.AlimentaAcaoAPP.Entities.DTOs.RendaDTO;
import com.example.AlimentaAcaoAPP.Entities.DTOs.UsuarioDTO;
import com.example.AlimentaAcaoAPP.Services.DoacaoService;
import com.example.AlimentaAcaoAPP.Services.UsuarioService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PreAuthorize("hasAnyRole('ADMIN','BENEFICIARIO')")
    @GetMapping
    public ResponseEntity<List<PessoaDTO>> listaTodosUsuarios(){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.listaUsuarios());
    }

    @PostMapping
    public ResponseEntity<String> criaUsuario(@Valid @RequestBody PessoaDTO usuario) {
        usuarioService.criaUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário criado com sucesso!");
    }

    @PreAuthorize("hasAnyRole('ADMIN','BENEFICIARIO')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> cadastrarRendaTotal(@PathVariable Integer id, @Valid @RequestBody RendaDTO rendaDTO) {
        BigDecimal rendaPerCapita = rendaDTO.valorRendaTotal()
            .divide(BigDecimal.valueOf(rendaDTO.quantidadePessoas()), 2, RoundingMode.HALF_UP);

        boolean beneficiario = rendaPerCapita.compareTo(new BigDecimal("218.00")) <= 0;

        usuarioService.atualizarRendaERedefinirBeneficio(id, rendaPerCapita, beneficiario, rendaDTO.quantidadePessoas());

        if (beneficiario) {
            return ResponseEntity.ok("Renda cadastrada. Usuário é um possível beneficiário.");
        } else {
            return ResponseEntity.ok("Renda cadastrada. Usuário não é elegível como beneficiário.");
        }
    }
}

