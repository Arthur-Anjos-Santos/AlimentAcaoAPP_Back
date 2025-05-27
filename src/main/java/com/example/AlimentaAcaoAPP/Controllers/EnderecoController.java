package com.example.AlimentaAcaoAPP.Controllers;
import com.example.AlimentaAcaoAPP.Entities.EnderecoViaCep;
import com.example.AlimentaAcaoAPP.Entities.DTOs.EnderecoResponseDTO;
import com.example.AlimentaAcaoAPP.Infra.Gateway.ConsultaCep;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private ConsultaCep cepService;

    @GetMapping("/cep/{cep}")
    public ResponseEntity<EnderecoResponseDTO> buscarEnderecoPorCep(@PathVariable String cep) {
        EnderecoViaCep endereco = cepService.buscarEnderecoPorCep(cep);

        if (endereco == null || endereco.getCep() == null) {
            return ResponseEntity.notFound().build();
        }

        EnderecoResponseDTO response = new EnderecoResponseDTO(
            endereco.getLogradouro(),
            endereco.getBairro(),
            endereco.getLocalidade(),
            endereco.getUf()
        );

        return ResponseEntity.ok(response);
    }
}

