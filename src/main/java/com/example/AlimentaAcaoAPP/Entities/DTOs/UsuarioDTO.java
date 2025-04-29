package com.example.AlimentaAcaoAPP.Entities.DTOs;

import com.example.AlimentaAcaoAPP.Entities.Enums.TipoUsuario;
import com.example.AlimentaAcaoAPP.Entities.Usuario;
import jakarta.persistence.Column;

import java.util.Date;
import java.util.Objects;

public class UsuarioDTO {

    private String nome;

    private String cpf;

    private String senha;

    private Date dataNascimento;

    private String email;

    private TipoUsuario tipoUsuario;

    public UsuarioDTO(){

    }

    public UsuarioDTO(Usuario usuario){
        this.nome = usuario.getNome();
        this.cpf = usuario.getCpf();
        this.senha = usuario.getSenha();
        this.dataNascimento = usuario.getDataNascimento();
        this.email = usuario.getEmail();
        this.tipoUsuario = getTipoUsuario();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof UsuarioDTO that)) return false;
        return Objects.equals(nome, that.nome) && Objects.equals(cpf, that.cpf) && Objects.equals(senha, that.senha) && Objects.equals(dataNascimento, that.dataNascimento) && Objects.equals(email, that.email) && tipoUsuario == that.tipoUsuario;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, cpf, senha, dataNascimento, email, tipoUsuario);
    }
}
