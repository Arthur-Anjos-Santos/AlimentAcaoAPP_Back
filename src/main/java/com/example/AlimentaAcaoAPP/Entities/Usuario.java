package com.example.AlimentaAcaoAPP.Entities;

import com.example.AlimentaAcaoAPP.Entities.DTOs.UsuarioDTO;
import com.example.AlimentaAcaoAPP.Entities.Enums.TipoUsuario;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract  class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (length = 120, nullable = false)
    private String nome;

    @Column (length = 11, nullable = false)
    private String cpf;

    @Column (length = 500, nullable = false)
    private String senha;

    @Column (nullable = false)
    private Date dataNascimento;

    @Column (length = 120, nullable = false)
    private String email;

    @Column (nullable = false)
    private TipoUsuario tipoUsuario;

    public Usuario(){

    }

    public Usuario(UsuarioDTO usuario){
        this.nome = usuario.getNome();
        this.cpf = usuario.getCpf();
        this.senha = usuario.getSenha();
        this.dataNascimento = usuario.getDataNascimento();
        this.email = usuario.getEmail();
        this.tipoUsuario = usuario.getTipoUsuario();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        if (!(o instanceof Usuario usuario)) return false;
        return Objects.equals(id, usuario.id) && Objects.equals(nome, usuario.nome) && Objects.equals(cpf, usuario.cpf) && Objects.equals(senha, usuario.senha) && Objects.equals(dataNascimento, usuario.dataNascimento) && Objects.equals(email, usuario.email) && tipoUsuario == usuario.tipoUsuario;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, cpf, senha, dataNascimento, email, tipoUsuario);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.tipoUsuario == TipoUsuario.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),
                new SimpleGrantedAuthority("ROLE_BENEFICIARIO"));
        else return List.of(new SimpleGrantedAuthority("ROLE_BENEFICIARIO"));
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}