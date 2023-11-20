package CadastroPOO.repository;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import CadastroPOO.model.PessoaJuridica;

public class PessoaJuridicaRepository 
{
    
   private ArrayList<PessoaJuridica> pessoasJuridicas = new ArrayList<>();

    //  GET por id
    public PessoaJuridica getById(int id) 
    {
        for (PessoaJuridica person : pessoasJuridicas) 
        {
            if (person.getId() == id) {
                return person;
            }
        }
        return null;
    }

    // GET todos
    public ArrayList<PessoaJuridica> getAll() 
    {
        return pessoasJuridicas;
    }

    // REMOVE
    public void remove(int id) 
    {
        for (int i = 0; i < pessoasJuridicas.size(); i++) 
        {
            PessoaJuridica person = pessoasJuridicas.get(i);
            if (person.getId() == id) 
            {
                pessoasJuridicas.remove(i);
                break;
            }
        }
    }

    // UPDATE
    public void update(PessoaJuridica pessoaJuridica) 
    {
        for (int i = 0; i < pessoasJuridicas.size(); i++) 
        {
            PessoaJuridica person = pessoasJuridicas.get(i);
            if (person.getId() == pessoaJuridica.getId()) 
            {
                pessoasJuridicas.set(i, pessoaJuridica);
                break;
            }
        }
    }

    // ADD
    public void add(PessoaJuridica pessoaJuridica) 
    {
        pessoasJuridicas.add(pessoaJuridica);
    }

    // Arquivos BIN
    public void createBIN(String arquivo) throws IOException 
    {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(arquivo));
        outputStream.writeObject(pessoasJuridicas);
        outputStream.close();
    }

    public void getBIN(String arquivo) throws IOException, ClassNotFoundException 
    {
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(arquivo));
        pessoasJuridicas = (ArrayList<PessoaJuridica>) inputStream.readObject();
        inputStream.close();
    }
}
