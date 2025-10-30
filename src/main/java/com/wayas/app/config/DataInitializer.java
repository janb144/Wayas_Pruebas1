package com.wayas.app.config;

import com.wayas.app.model.Usuario;
import com.wayas.app.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (usuarioRepository.findByUsername("admin").isEmpty()) {
            Usuario admin = new Usuario();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("12345")); // Contraseña '12345' encriptada
            admin.setRol("ADMIN");
            admin.setActivo(true);
            usuarioRepository.save(admin);
            System.out.println(">>> Usuario 'admin' (pass: '12345') creado <<<");
        }
         // Puedes añadir más usuarios si lo necesitas
         if (usuarioRepository.findByUsername("user").isEmpty()) {
            Usuario user = new Usuario();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("password")); // Contraseña 'password' encriptada
            user.setRol("USER"); // Rol diferente
            user.setActivo(true);
            usuarioRepository.save(user);
            System.out.println(">>> Usuario 'user' (pass: 'password') creado <<<");
        }
    }
}