package com.CadastroDB.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "PessoaJuridica")
public class PessoaJuridica extends Pessoa {

    @Column(unique = true)
    private String cnpj;

    public String pegarCnpj() {
        return this.cnpj;
    }

    public void criarCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

}
