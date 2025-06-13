package com.example.AlimentaAcaoAPP.Services;

import com.example.AlimentaAcaoAPP.Entities.Doacao;
import com.example.AlimentaAcaoAPP.Entities.Pessoa;
import com.example.AlimentaAcaoAPP.Entities.DTOs.DoacaoDTO;
import com.example.AlimentaAcaoAPP.Entities.DTOs.RendaDTO;
import com.example.AlimentaAcaoAPP.Repository.DoacaoRepository;
import com.example.AlimentaAcaoAPP.Repository.PessoaRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoacaoService {
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private DoacaoRepository doacaoRepository;

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

    public void registrarDoacao(DoacaoDTO dto, Integer doadorId) {
        Pessoa doador = usuarioService.buscarPorId(doadorId);

        Doacao doacao = new Doacao();
        doacao.setDoador(doador);
        doacao.setTipo(dto.doacao());
        doacao.setQuantidade(dto.quantidade());
        doacao.setObservacao(dto.comentario());
        doacao.setData(LocalDateTime.now());

        doacaoRepository.save(doacao);
    }
}
