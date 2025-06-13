package com.example.AlimentaAcaoAPP.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.AlimentaAcaoAPP.Entities.Doacao;

public interface DoacaoRepository extends JpaRepository<Doacao, Integer> {
}
