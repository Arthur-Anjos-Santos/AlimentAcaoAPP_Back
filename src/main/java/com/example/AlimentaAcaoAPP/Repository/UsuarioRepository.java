package com.example.AlimentaAcaoAPP.Repository;

import com.example.AlimentaAcaoAPP.Entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository <Usuario, Integer> {
}
