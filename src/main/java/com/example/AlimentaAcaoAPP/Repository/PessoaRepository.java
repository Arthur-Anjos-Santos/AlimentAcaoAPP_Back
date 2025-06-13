package com.example.AlimentaAcaoAPP.Repository;

import com.example.AlimentaAcaoAPP.Entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepository  extends JpaRepository<Pessoa, Integer> {
    Optional<Pessoa> findByEmail(String email);
}
