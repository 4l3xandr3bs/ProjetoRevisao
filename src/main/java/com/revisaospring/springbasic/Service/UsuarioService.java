package com.revisaospring.springbasic.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.revisaospring.springbasic.Entity.Usuario;
import com.revisaospring.springbasic.Repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired 
    private UsuarioRepository oUsuarioRepository;
    @Autowired 
    private PasswordEncoder encoder;

    public void cadastrarUsuario (Usuario oUsuario){

        //realizando a cripitrogarfia da senha
        oUsuario.setPassword(encoder.encode(oUsuario.getPassword()));

        //setando um valor padrao para a role do usuario
        if(oUsuario.getRole() ==null || oUsuario.getRole().isEmpty()){
            oUsuario.setRole("ROLE_USER");
        }

        oUsuarioRepository.save(oUsuario);

    }

    // lisa os usuarios
    public List<Usuario>  listarTodosUsuarios(){
        return oUsuarioRepository.findAll();
    }

   
}