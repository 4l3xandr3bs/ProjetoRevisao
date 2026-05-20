package com.revisaospring.springbasic.Service;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.revisaospring.springbasic.Entity.Usuario;
import com.revisaospring.springbasic.Repository.UsuarioRepository;

@Service

public class CustomUserDetailService implements UserDetailsService{

    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailService(UsuarioRepository oUsuarioRepository){
        this.usuarioRepository = oUsuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       
        Usuario oUsuario = usuarioRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado!"));

        return User.builder()
        .username(oUsuario.getUsername())
        .password(oUsuario.getPassword())
        .roles(oUsuario.getRole().replace("ROLE_", ""))
        .build();
    }
}
