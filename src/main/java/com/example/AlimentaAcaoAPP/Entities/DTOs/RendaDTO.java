package com.example.AlimentaAcaoAPP.Entities.DTOs;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record RendaDTO(
        @NotNull @Min(1) Integer quantidadePessoas,
        @NotNull @DecimalMin(value = "0.0", inclusive = false) BigDecimal valorRendaTotal
) {}
