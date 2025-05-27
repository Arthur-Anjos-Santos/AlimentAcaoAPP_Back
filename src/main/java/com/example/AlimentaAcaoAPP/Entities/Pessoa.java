package com.example.AlimentaAcaoAPP.Entities;

import com.example.AlimentaAcaoAPP.Entities.DTOs.PessoaDTO;
import com.example.AlimentaAcaoAPP.Entities.DTOs.UsuarioDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


import java.math.BigDecimal;

@Entity
public class Pessoa extends Usuario {

    @Column(length = 120)
    private Integer quantidadePessoas;
    @Column (length = 120)

    private BigDecimal valorRendaPercapita;
    private BigDecimal rendaTotal;
    private Boolean ehBeneficiario;

    @ManyToOne
    @JoinColumn(name = "endereco_id", referencedColumnName = "id", nullable = false)
    private Endereco endereco;


    public Pessoa(UsuarioDTO usuario, PessoaDTO pessoaDTO) {
        super(usuario);
        this.rendaTotal = pessoaDTO.getRendaTotal();
        this.endereco = new Endereco(pessoaDTO.getEndereco());
        this.quantidadePessoas = pessoaDTO.getQuantidadePessoas();
        this.ehBeneficiario = pessoaDTO.isEhBeneficiario();
    }


    public Pessoa() {

    }

    public Integer getQuantidadePessoas() {
        return quantidadePessoas;
    }

    public Usuario getUsuario() {
        return this;
    }
    public void setQuantidadePessoas(Integer quantidadePessoas) {
        this.quantidadePessoas = quantidadePessoas;
    }

    public BigDecimal getValorRendaPercapita() {
        return valorRendaPercapita;
    }

    public BigDecimal getRendaTotal() {
        return rendaTotal;
    }

    public void setRendaTotal(BigDecimal rendaTotal) {
        this.rendaTotal = rendaTotal;
    }

    public Boolean isEhBeneficiario() {
        return ehBeneficiario;
    }

    public void setEhBeneficiario(Boolean ehBeneficiario) {
        this.ehBeneficiario = ehBeneficiario;
    }

    public void setValorRendaPercapita(BigDecimal valorRendaPercapita) {
        this.valorRendaPercapita = valorRendaPercapita;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
