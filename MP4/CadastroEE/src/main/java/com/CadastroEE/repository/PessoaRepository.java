package com.CadastroEE.repository;

import org.springframework.data.repository.CrudRepository;
import com.CadastroEE.model.Pessoa;

public interface PessoaRepository extends CrudRepository<Pessoa, Long> {}
