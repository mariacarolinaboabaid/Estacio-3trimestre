package com.CadastroServer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CadastroServer.model.Produto;
import com.CadastroServer.repository.ProdutoRepository;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto getById(Long id) {
        return produtoRepository.findById(id).orElse(null);
    }

     public List<Produto> listAll() {
        return produtoRepository.findAll();
    }

    public Produto create(Produto product) {
        return produtoRepository.save(product);
    }

    public Produto update(Long id, Produto product) {
        if (produtoRepository.existsById(id)) {
            product.setId(id);
            return produtoRepository.save(product);
        }
        return null;
    }

    public void delete(Long id) {
        produtoRepository.deleteById(id);
    }
}
