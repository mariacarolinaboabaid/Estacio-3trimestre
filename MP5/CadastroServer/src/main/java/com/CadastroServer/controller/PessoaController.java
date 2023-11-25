package com.CadastroServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.CadastroServer.model.Pessoa;
import com.CadastroServer.service.PessoaService;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public List<Pessoa> listAll() {
        return pessoaService.listAll();
    }

    @GetMapping("/{id}")
    public Pessoa getById(@PathVariable Long id) {
        return pessoaService.getById(id);
    }

    @PostMapping
    public Pessoa create(@RequestBody Pessoa person) {
        return pessoaService.create(person);
    }

    @PutMapping("/{id}")
    public Pessoa update(@PathVariable Long id, @RequestBody Pessoa person) {
        return pessoaService.update(id, person);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        pessoaService.delete(id);
    }

    public Pessoa findPessoa(int pessoaId) {
        return null;
    }
}
