package com.example.AlimentaAcaoAPP.Entities.DTOs;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record SolitacaoDTO(@NotNull Integer quantidadePessoas,
                           @NotNull BigDecimal valorRendaPercapita,
                           @NotNull EnderecoDTO endereco) {
}
