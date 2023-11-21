package com.CadastroDB;

import com.CadastroDB.dao.PessoaFisicaDAO;
import com.CadastroDB.dao.PessoaJuridicaDAO;
import com.CadastroDB.model.PessoaFisica;
import com.CadastroDB.model.PessoaJuridica;

public class CadastroDbApplicationTests {

    public static void main(String[] args) 
    {
        testPessoaFisicaOperations();
        testPessoaJuridicaOperations();
    }

    private static void testPessoaFisicaOperations() {
        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
    
        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.criarNome("Joao");
        pessoaFisica.criarLogradouro("Rua 11, Centro");
        pessoaFisica.criarCidade("Riacho do Sul");
        pessoaFisica.criarEstado("PA");
        pessoaFisica.criarTelefone("1111-111");
        pessoaFisica.criarEmail("joao@riacho.com");
        pessoaFisica.criarCpf("11111111111");
        pessoaFisicaDAO.adicionar(pessoaFisica);
    

        if (pessoaFisica.pegarId() != null) 
        {
            pessoaFisica.criarNome("Novo Nome");
            pessoaFisicaDAO.alterar(pessoaFisica);
    
            System.out.println("Pessoas Físicas registradas");
            pessoaFisicaDAO.obterTodos().forEach(System.out::println);
    
            pessoaFisicaDAO.excluir(pessoaFisica.pegarId());
        } 
        else 
        {
            System.out.println("Falha ao persistir Pessoa Física no banco.");
        }
    }
    
    private static void testPessoaJuridicaOperations() {
        PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();
    

        PessoaJuridica pessoaJuridica = new PessoaJuridica();
        pessoaJuridica.criarNome("JJC");
        pessoaJuridica.criarLogradouro("Rua 11, Centro");
        pessoaJuridica.criarCidade("Riacho do Sul");
        pessoaJuridica.criarEstado("PA");
        pessoaJuridica.criarTelefone("1212-1212");
        pessoaJuridica.criarEmail("jjc@riacho.com");
        pessoaJuridica.criarCnpj("11111111111111");
        pessoaJuridicaDAO.adicionar(pessoaJuridica);
    
  
        if (pessoaJuridica.pegarId() != null) {

            pessoaJuridica.criarNome("Novo Nome");
            pessoaJuridicaDAO.alterar(pessoaJuridica);
    

            System.out.println("Pessoas Jurídicas registradas:");
            pessoaJuridicaDAO.obterTodos().forEach(System.out::println);
    

            pessoaJuridicaDAO.excluir(pessoaJuridica.pegarId());
        } 
        else 
        {
            System.out.println("Falha ao gravar dados no banco.");
        }
    }
    
}