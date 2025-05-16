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

    @ManyToOne
    @JoinColumn(name = "endereco_id", referencedColumnName = "id", nullable = false)
    private Endereco endereco;


    public Pessoa(UsuarioDTO usuario, PessoaDTO pessoaDTO) {
        super(usuario);
        this.quantidadePessoas = pessoaDTO.getQuantidadePessoas();
        this.valorRendaPercapita = pessoaDTO.getValorRendaPercapita();
        this.endereco = new Endereco(pessoaDTO.getEndereco());
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
