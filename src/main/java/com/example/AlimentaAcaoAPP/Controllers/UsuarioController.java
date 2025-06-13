package com.example.AlimentaAcaoAPP.Controllers;

import com.example.AlimentaAcaoAPP.Entities.DTOs.PessoaDTO;
import com.example.AlimentaAcaoAPP.Entities.DTOs.RendaDTO;
import com.example.AlimentaAcaoAPP.Entities.DTOs.ResultadoBeneficioDTO;
import com.example.AlimentaAcaoAPP.Services.DoacaoService;
import com.example.AlimentaAcaoAPP.Services.QrCodeService;
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
    private UsuarioService usuarioService;

    @Autowired
    private QrCodeService qrCodeService;

    @Autowired
    private DoacaoService doacaoService;

    @PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
    @GetMapping
    public ResponseEntity<List<PessoaDTO>> listaTodosUsuarios() {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.listaUsuarios());
    }

    @PostMapping
    public ResponseEntity<String> criaUsuario(@Valid @RequestBody PessoaDTO usuario) {
        usuarioService.criaUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário criado com sucesso!");
    }

    @PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
    @PutMapping("/{id}")
    public ResponseEntity<ResultadoBeneficioDTO> cadastrarRendaTotal(
            @PathVariable Integer id,
            @Valid @RequestBody RendaDTO rendaDTO) {

        DoacaoService.BeneficioRenda resultado = doacaoService.calcularBeneficioRenda(rendaDTO);

        usuarioService.atualizarRendaERedefinirBeneficio(
                id,
                resultado.getRendaPerCapita(),
                resultado.isBeneficiario(),
                rendaDTO.quantidadePessoas(),
                rendaDTO.valorRendaTotal()
        );

        String qrCodeBase64 = qrCodeService.obterQrCode(id, resultado.isBeneficiario());

        ResultadoBeneficioDTO responseDTO = new ResultadoBeneficioDTO(
                resultado.getRendaPerCapita(),
                resultado.isBeneficiario(),
                qrCodeBase64
        );

        return ResponseEntity.ok(responseDTO);
    }
}
