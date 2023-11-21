package com.CadastroDB.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "PessoaFisica")
public class PessoaFisica extends Pessoa {

    @Column(unique = true)
    private String cpf;

    public String pegarCpf() {
        return this.cpf;
    }

    public void criarCpf(String cpf) {
        this.cpf = cpf;
    }

    
}
