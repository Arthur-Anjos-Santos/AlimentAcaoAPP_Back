package com.example.AlimentaAcaoAPP.Entities.DTOs;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


public record DoacaoDTO(
        @NotNull String doacao,
        @NotNull @Min(1) Integer quantidade,
        String comentario
) {}
