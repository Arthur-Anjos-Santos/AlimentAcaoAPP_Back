package com.example.AlimentaAcaoAPP.Entities.DTOs;

import java.math.BigDecimal;

public record ResultadoBeneficioDTO(
    BigDecimal rendaPerCapita,
    boolean ehBeneficiario
) {}
