package com.CadastroEE.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.CadastroEE.model.Produto;
import com.CadastroEE.repository.ProdutoRepository;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @GetMapping
    public String obterProdutos(Model model) {
        model.addAttribute("produtos", produtoRepository.findAll());
        return "lista";
    }

    @GetMapping("/delete/{id}")
    public String excluirProduto(@PathVariable Long id) {
        produtoRepository.deleteById(id);
        return "redirect:/produtos";
    }

    @PostMapping("/salvar")
    public String salvarMudancas(@ModelAttribute Produto produto) {
        produtoRepository.save(produto);
        return "redirect:/produtos";
    }

    @GetMapping("/formulario")
    public String formulario(Model model) {
        model.addAttribute("produto", new Produto());
        return "editar";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não válido: " + id));
        model.addAttribute("produto", produto);
        return "editar"; 
    }

}
