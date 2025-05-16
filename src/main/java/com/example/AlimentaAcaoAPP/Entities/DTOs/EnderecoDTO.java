package com.example.AlimentaAcaoAPP.Entities.DTOs;

import com.example.AlimentaAcaoAPP.Entities.Endereco;
import jakarta.validation.constraints.NotBlank;

public record EnderecoDTO(@NotBlank String rua,
                          @NotBlank String numero,
                          @NotBlank String bairro,
                          @NotBlank String cidade,
                          @NotBlank String estado,
                          @NotBlank String cep,
                          @NotBlank String complemento
) {
    public EnderecoDTO(Endereco endereco) {
        this(
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getCep(),
                endereco.getComplemento()
        );
    }
}
