package com.CadastroServer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Movimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private int quantity;
    private double price;
    private String type;

    @ManyToOne
    private Usuario user;

    @ManyToOne
    private Pessoa person;

    @ManyToOne
    private Produto product;

    // Métodos
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Usuario getUser() {
        return this.user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public Pessoa getPerson() {
        return this.person;
    }

    public void setPerson(Pessoa person) {
        this.person = person;
    }

    public Produto getProduto() {
        return this.product;
    }

    public void setProduto(Produto product) {
        this.product = product;
    }
}
