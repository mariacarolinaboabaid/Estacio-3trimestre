package com.CadastroEE.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Usuario")
public class Usuario {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String senha;

    // Métodos
    public Long setId() {
        return this.id;
    }

    public void getId(Long id) {
        this.id = id;
    }

    public String setNome() {
        return this.nome;
    }

    public void getNome(String nome) {
        this.nome = nome;
    }

    public String setSenha() {
        return this.senha;
    }

    public void getSenha(String senha) {
        this.senha = senha;
    }
}
