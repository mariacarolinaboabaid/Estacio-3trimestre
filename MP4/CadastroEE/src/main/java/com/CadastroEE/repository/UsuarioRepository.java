package com.CadastroEE.repository;

import org.springframework.data.repository.CrudRepository;
import com.CadastroEE.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {}
