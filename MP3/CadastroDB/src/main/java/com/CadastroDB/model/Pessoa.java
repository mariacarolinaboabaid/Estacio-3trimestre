package com.CadastroDB.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;
    private String logradouro;
    private String cidade;
    private String estado;
    private String telefone;
    private String email;


    public Long pegarId() {
        return this.id;
    }

    public void criarId(Long id) {
        this.id = id;
    }

    public String pegarNome() {
        return this.nome;
    }

    public void criarNome(String nome) {
        this.nome = nome;
    }

    public String pegarLogradouro() {
        return this.logradouro;
    }

    public void criarLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String pegarCidade() {
        return this.cidade;
    }

    public void criarCidade(String cidade) {
        this.cidade = cidade;
    }

    public String pegarEstado() {
        return this.estado;
    }

    public void criarEstado(String estado) {
        this.estado = estado;
    }

    public String pegarTelefone() {
        return this.telefone;
    }

    public void criarTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String pegarEmail() {
        return this.email;
    }

    public void criarEmail(String email) {
        this.email = email;
    }

}
