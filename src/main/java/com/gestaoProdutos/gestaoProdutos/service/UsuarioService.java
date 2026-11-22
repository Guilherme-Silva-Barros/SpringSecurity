package com.gestaoProdutos.gestaoProdutos.service;


import com.gestaoProdutos.gestaoProdutos.entity.Usuario;
import com.gestaoProdutos.gestaoProdutos.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String nome)throws UsernameNotFoundException {
        Usuario user = usuarioRepository.findByNome(nome).orElseThrow(()
                ->new UsernameNotFoundException("Usuário não encontrado"));
        return User.builder()
                .username(user.getNome())
                .password(user.getSenha())
                .roles(user.getRole())
                .build();
    }

}
