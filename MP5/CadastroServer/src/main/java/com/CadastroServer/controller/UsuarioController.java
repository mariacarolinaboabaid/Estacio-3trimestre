package com.CadastroServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.CadastroServer.model.Usuario;
import com.CadastroServer.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> listAll() {
        return usuarioService.listAll();
    }

    @GetMapping("/{id}")
    public Usuario getById(@PathVariable Long id) {
        return usuarioService.getById(id);
    }

    @PostMapping
    public Usuario create(@RequestBody Usuario user) {
        return usuarioService.create(user);
    }

    @PutMapping("/{id}")
    public Usuario update(@PathVariable Long id, @RequestBody Usuario user) {
        return usuarioService.update(id, user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        usuarioService.delete(id);
    }
}
