package com.CadastroServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CadastroServer.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
   
}
