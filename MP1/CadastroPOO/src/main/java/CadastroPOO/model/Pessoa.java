package CadastroPOO.model;

import java.io.Serializable;

public class Pessoa implements Serializable {

    private int id;
    private String name;

    public Pessoa() {}

    public Pessoa(int id, String nome) {
        this.id = id;
        this.name = nome;
    }

    // Get
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Set
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String nome) {
        this.name = name;
    }


    // Read

    public void read() {
        System.out.println("ID: " + id);
        System.out.println("Nome: " + name);
    }
}
