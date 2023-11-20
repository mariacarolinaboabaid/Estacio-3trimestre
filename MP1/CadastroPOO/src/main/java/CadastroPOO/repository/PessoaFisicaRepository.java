package CadastroPOO.repository;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import CadastroPOO.model.PessoaFisica;

public class PessoaFisicaRepository 
{

    private ArrayList<PessoaFisica> pessoasFisicas = new ArrayList<>();

    // GET por id
    public PessoaFisica getById(int id) {

        for (PessoaFisica person : pessoasFisicas) 
        {
            if (person.getId() == id) {
                return person;
            }
        }
        return null;
    }

    // GET todos
    public ArrayList<PessoaFisica> getAll() 
    {
        return pessoasFisicas;
    }

    // ADD
    public void add(PessoaFisica pessoaFisica) 
    {
        pessoasFisicas.add(pessoaFisica);
    }

    // UPDATE
    public void update(PessoaFisica pessoaFisica) 
    {
        for (int i = 0; i < pessoasFisicas.size(); i++) 
        {
            PessoaFisica person = pessoasFisicas.get(i);
            if (person.getId() == pessoaFisica.getId()) 
            {
                pessoasFisicas.set(i, pessoaFisica);
                break;
            }
        }
    }

    // REMOVE
    public void remove(int id) 
    {
        for (int i = 0; i < pessoasFisicas.size(); i++) 
        {
            PessoaFisica person = pessoasFisicas.get(i);
            if (person.getId() == id) 
            {
                pessoasFisicas.remove(i);
                break;
            }
        }
    }


    // Arquivos BIN
    public void createBIN(String arquivo) throws IOException 
    {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(arquivo));
        outputStream.writeObject(pessoasFisicas);
        outputStream.close();
    }

    public void getBIN(String arquivo) throws IOException, ClassNotFoundException 
    {
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(arquivo));
        pessoasFisicas = (ArrayList<PessoaFisica>) inputStream.readObject();
        inputStream.close();
    }
}
