package com.CadastroServer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CadastroServer.model.Pessoa;
import com.CadastroServer.repository.PessoaRepository;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa getById(Long id) {
        return pessoaRepository.findById(id).orElse(null);
    }

     public List<Pessoa> listAll() {
        return pessoaRepository.findAll();
    }

    public Pessoa create(Pessoa person) {
        return pessoaRepository.save(person);
    }

    public Pessoa update(Long id, Pessoa person) {
        if (pessoaRepository.existsById(id)) {
            person.setId(id);
            return pessoaRepository.save(person);
        }
        return null;
    }

    public void delete(Long id) {
        pessoaRepository.deleteById(id);
    }
}
