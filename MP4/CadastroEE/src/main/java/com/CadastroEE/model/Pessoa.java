package com.CadastroEE.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String logradouro;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private PessoaFisica pessoaFisica;

    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private PessoaJuridica pessoaJuridica;

    // Métodos
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

    public PessoaFisica pegarPessoaFisica() {
        return this.pessoaFisica;
    }

    public void criarPessoaFisica(PessoaFisica pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }

    public PessoaJuridica pegarPessoaJuridica() {
        return this.pessoaJuridica;
    }

    public void criarPessoaJuridica(PessoaJuridica pessoaJuridica) {
        this.pessoaJuridica = pessoaJuridica;
    }

}

