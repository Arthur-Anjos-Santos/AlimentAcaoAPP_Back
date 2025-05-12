package com.example.AlimentaAcaoAPP.Repository;

import com.example.AlimentaAcaoAPP.Entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByCpfAndSenha(String cpf, String senha);
}
