package com.example.AlimentaAcaoAPP.Entities.DTOs;

import com.example.AlimentaAcaoAPP.Entities.Pessoa;

import java.math.BigDecimal;

public class PessoaDTO {

    private UsuarioDTO usuario;
    private Integer quantidadePessoas;
    private EnderecoDTO endereco;
    private Double rendaTotal;
    private boolean ehBeneficiario;

    public PessoaDTO() {}
    public PessoaDTO(Pessoa pessoa) {
        this.usuario = new UsuarioDTO(pessoa);
        this.quantidadePessoas = pessoa.getQuantidadePessoas();
        this.rendaTotal = pessoa.getRendaTotal();
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

    public Double getRendaTotal() {
        return rendaTotal;
    }

    public void setRendaTotal(Double rendaTotal) {
        this.rendaTotal = rendaTotal;
    }

    public boolean isEhBeneficiario() {
        return ehBeneficiario;
    }

    public void setEhBeneficiario(boolean ehBeneficiario) {
        this.ehBeneficiario = ehBeneficiario;
    }

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
    }
}
