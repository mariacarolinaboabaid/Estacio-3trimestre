package CadastroPOO.model;

import java.io.Serializable;

public class PessoaJuridica extends Pessoa implements Serializable {

    private String cnpj;

    public PessoaJuridica(int id, String name, String cnpj) {
        super(id, name);
        this.cnpj = cnpj;
    }

    @Override
    public void read() {
        super.read();
        System.out.println("CNPJ: " + cnpj);
    }
}
