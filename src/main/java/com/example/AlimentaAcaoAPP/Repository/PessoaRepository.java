package com.example.AlimentaAcaoAPP.Repository;

import com.example.AlimentaAcaoAPP.Entities.Pessoa;
import com.example.AlimentaAcaoAPP.Entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepository  extends JpaRepository<Pessoa, Integer> {
    //Optional<Usuario> findByCpfAndSenha(String cpf, String senha);
    Optional<Pessoa> findByEmail(String email);
}
