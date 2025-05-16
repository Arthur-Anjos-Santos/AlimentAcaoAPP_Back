package com.example.AlimentaAcaoAPP.Entities.DTOs;

import com.example.AlimentaAcaoAPP.Entities.Pessoa;

import java.math.BigDecimal;

public class PessoaDTO {

    private UsuarioDTO usuario;
    private Integer quantidadePessoas;
    private BigDecimal valorRendaPercapita;
    private EnderecoDTO endereco;

    public PessoaDTO() {}
    public PessoaDTO(Pessoa pessoa) {
        this.usuario = new UsuarioDTO(pessoa);
        this.quantidadePessoas = pessoa.getQuantidadePessoas();
        this.valorRendaPercapita = pessoa.getValorRendaPercapita();
        this.endereco = new EnderecoDTO(pessoa.getEndereco());
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public Integer getQuantidadePessoas() {
        return quantidadePessoas;
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

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
    }
}
