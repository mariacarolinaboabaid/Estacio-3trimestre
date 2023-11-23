package com.CadastroEE.repository;

import org.springframework.data.repository.CrudRepository;
import com.CadastroEE.model.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Long> {}
