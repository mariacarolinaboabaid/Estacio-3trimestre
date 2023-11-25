package com.CadastroServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.CadastroServer.model.Movimento;
import com.CadastroServer.service.MovimentoService;

import java.util.List;

@RestController
@RequestMapping("/movimentos")
public class MovimentoController {

    @Autowired
    private MovimentoService movimentoService;

    @GetMapping
    public List<Movimento> listAll() {
        return movimentoService.listAll();
    }

    @GetMapping("/{id}")
    public Movimento getById(@PathVariable Long id) {
        return movimentoService.getById(id);
    }

    @PostMapping
    public Movimento create(@RequestBody Movimento moviment) {
        return movimentoService.create(moviment);
    }

    @PutMapping("/{id}")
    public Movimento update(@PathVariable Long id, @RequestBody Movimento moviment) {
        return movimentoService.update(id, moviment);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        movimentoService.delete(id);
    }

    public void updateQuantity(int productId, int quantity) {
    }
    
}
