package com.gestaoProdutos.gestaoProdutos.config;

import com.gestaoProdutos.gestaoProdutos.entity.Usuario;
import com.gestaoProdutos.gestaoProdutos.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInicializer implements CommandLineRunner {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder encoder;
    public DataInicializer(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.encoder = passwordEncoder;
    }

    @Override

    public void run(String... args){
        CriarAutomatico("funcionario", "123456", "FUNCIONARIO");
        CriarAutomatico("gerente", "123456", "GERENTE");
    }

    private void CriarAutomatico (String nome, String senha, String role){
        if(usuarioRepository.findByNome(nome).isEmpty()){
            usuarioRepository.save(new Usuario(nome, encoder.encode(senha), role));
        }
    }

}
