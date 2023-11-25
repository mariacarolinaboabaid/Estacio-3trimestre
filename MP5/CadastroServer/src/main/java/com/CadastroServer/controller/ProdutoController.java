package com.CadastroServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.CadastroServer.model.Produto;
import com.CadastroServer.service.ProdutoService;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<Produto> listAll() {
        return produtoService.listAll();
    }

    @GetMapping("/{id}")
    public Produto getById(@PathVariable Long id) {
        return produtoService.getById(id);
    }

    @PostMapping
    public Produto create(@RequestBody Produto product) {
        return produtoService.create(product);
    }

    @PutMapping("/{id}")
    public Produto update(@PathVariable Long id, @RequestBody Produto product) {
        return produtoService.update(id, product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        produtoService.delete(id);
    }
}
