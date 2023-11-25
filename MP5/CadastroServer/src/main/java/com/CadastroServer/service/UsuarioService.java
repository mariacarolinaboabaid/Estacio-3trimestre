package com.CadastroServer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CadastroServer.model.Usuario;
import com.CadastroServer.repository.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario getById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

     public List<Usuario> listAll() {
        return usuarioRepository.findAll();
    }

    public Usuario create(Usuario user) {
        return usuarioRepository.save(user);
    }

    public Usuario update(Long id, Usuario user) {
        if (usuarioRepository.existsById(id)) {
            user.setId(id);
            return usuarioRepository.save(user);
        }
        return null;
    }

    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }
}
