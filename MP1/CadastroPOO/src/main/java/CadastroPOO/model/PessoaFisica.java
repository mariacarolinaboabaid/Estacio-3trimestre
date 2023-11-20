package CadastroPOO.model;

import java.io.Serializable;

public class PessoaFisica extends Pessoa implements Serializable {

    private String cpf;
    private int age;

    public PessoaFisica(int id, String name, String cpf, int age) {
        super(id, name);
        this.cpf = cpf;
        this.age = age;
    }

    @Override
    public void read() {
        super.read();
        System.out.println("CPF: " + cpf);
        System.out.println("Idade: " + age);
    }
}
