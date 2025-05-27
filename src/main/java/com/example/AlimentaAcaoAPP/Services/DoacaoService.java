package com.example.AlimentaAcaoAPP.Services;

import com.example.AlimentaAcaoAPP.Entities.DTOs.RendaDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

@Service
public class DoacaoService {

    public BeneficioRenda calcularBeneficioRenda(RendaDTO rendaDTO) {
        BigDecimal perCapita = rendaDTO.valorRendaTotal()
                .divide(BigDecimal.valueOf(rendaDTO.quantidadePessoas()), 2, RoundingMode.HALF_UP);

        boolean isBeneficiario = perCapita.compareTo(new BigDecimal("400.00")) <= 0;

        return new BeneficioRenda(perCapita, isBeneficiario);
    }

    public static class BeneficioRenda {
        private final BigDecimal rendaPerCapita;
        private final boolean beneficiario;

        public BeneficioRenda(BigDecimal rendaPerCapita, boolean beneficiario) {
            this.rendaPerCapita = rendaPerCapita;
            this.beneficiario = beneficiario;
        }

        public BigDecimal getRendaPerCapita() {
            return rendaPerCapita;
        }

        public boolean isBeneficiario() {
            return beneficiario;
        }
    }
}
