package com.CadastroServer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CadastroServer.model.Movimento;
import com.CadastroServer.repository.MovimentoRepository;

import java.util.List;

@Service
public class MovimentoService {

    @Autowired
    private MovimentoRepository movimentoRepository;

    public Movimento getById(Long id) {
        return movimentoRepository.findById(id).orElse(null);
    }

    public List<Movimento> listAll() {
        return movimentoRepository.findAll();
    }

    public Movimento create(Movimento moviment) {
        return movimentoRepository.save(moviment);
    }

    public Movimento update(Long id, Movimento moviment) {
        if (movimentoRepository.existsById(id)) {
            moviment.setId(id);
            return movimentoRepository.save(moviment);
        }
        return null;
    }

    public void delete(Long id) {
        movimentoRepository.deleteById(id);
    }
}
