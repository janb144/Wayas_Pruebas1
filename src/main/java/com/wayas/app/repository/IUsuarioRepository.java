package com.wayas.app.repository;

import com.wayas.app.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional; 

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByUsername(String username);
}
