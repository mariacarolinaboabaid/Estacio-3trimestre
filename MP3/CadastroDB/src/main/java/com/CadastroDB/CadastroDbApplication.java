package com.CadastroDB;

import com.CadastroDB.dao.PessoaFisicaDAO;
import com.CadastroDB.dao.PessoaJuridicaDAO;
import com.CadastroDB.model.PessoaFisica;
import com.CadastroDB.model.PessoaJuridica;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
@ComponentScan(basePackages = "com.CadastroDB")
public class CadastroDbApplication {

    public static void main(String[] args) 
    {
        SpringApplication.run(CadastroDbApplication.class, args);
    }

    @Bean
    public CommandLineRunner cadastroRunner(PessoaFisicaDAO pessoaFisicaDAO, PessoaJuridicaDAO pessoaJuridicaDAO) 
    {
        return args -> 
        {
            Scanner scanner = new Scanner(System.in);

            int opcao;
            do 
            {
                System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
                System.out.println("1 - Incluir Pessoa");
                System.out.println("2 - Alterar Pessoa");
                System.out.println("3 - Excluir Pessoa");
                System.out.println("4 - Buscar pelo ID");
                System.out.println("5 - Exibir todos");
                System.out.println("0 - Encerrar");
                System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine();  

                switch (opcao) {
                    // adicionar
                    case 1:
                        char novaPessoa = tipoPessoa(scanner);

                        if (novaPessoa == 'F') 
                        {
                            PessoaFisica pf = new PessoaFisica();
                            System.out.println("Insira os dados:");
                            addDadosPF(pf, scanner);
                            pessoaFisicaDAO.adicionar(pf);
                        } 
                        else if (novaPessoa == 'J') 
                        {
                            PessoaJuridica pj = new PessoaJuridica();
                            System.out.println("Insira os dados:");
                            addDadosPJ(pj, scanner);
                            pessoaJuridicaDAO.adicionar(pj);
                        } 
                        else 
                        {
                            System.out.println("A opção escolhida é inválida");
                        }
                        break;

                    // ALTERAR
                    case 2:
                        char dadosNovos = tipoPessoa(scanner);

                        if (dadosNovos == 'F') 
                        {
                            System.out.print("ID: ");
                            long id = scanner.nextLong();
                            scanner.nextLine();
                            PessoaFisica pessoaAlterada = pessoaFisicaDAO.obterPorId(id);

                            if (pessoaAlterada == null) 
                            {
                                System.out.println("ID não localizado.");

                            }
                             else 
                            {
                                System.out.println("Digite os novos dados:");
                                addDadosPF(pessoaAlterada, scanner);
                                pessoaFisicaDAO.alterar(pessoaAlterada);
                                System.out.println("Dados alterados com sucesso!");
                            }
                        } 
                        else if (dadosNovos == 'J') 
                        {
    
                            System.out.print("ID: ");
                            long id = scanner.nextLong();
                            scanner.nextLine(); // Limpar o buffer do teclado
                            PessoaJuridica pessoaAlterada = pessoaJuridicaDAO.obterPorId(id);

                            if (pessoaAlterada == null) 
                            {
                                System.out.println("ID não localizado.");
                                
                            } 
                            else 
                            {
                                System.out.println("Digite os novos dados:");
                                addDadosPJ(pessoaAlterada, scanner);
                                pessoaJuridicaDAO.alterar(pessoaAlterada);
                                System.out.println("Dados alterados com sucesso!");
                    
                            }
                        } 
                        else 
                        {
                            System.out.println("A opção escolhida é inválida");
                        }
                        break;

                    // EXCLUIR
                    case 3:
                        char excluir = tipoPessoa(scanner);

                        if (excluir == 'F') 
                        {
                            System.out.print("ID: ");
                            long id = scanner.nextLong();
                            scanner.nextLine(); 
                            PessoaFisica pessoa = pessoaFisicaDAO.obterPorId(id);

                            if (pessoa == null) 
                            {
                                System.out.println("ID não localizado.");
                                
                            } 
                            else 
                            {
                                pessoaFisicaDAO.excluir(id);
                                System.out.println("Excluído com sucesso!");
                            }
                        } 
                        else if (excluir == 'J') 
                        {
                            System.out.print("ID: ");
                            long id = scanner.nextLong();
                            scanner.nextLine(); 
                            PessoaJuridica pessoa = pessoaJuridicaDAO.obterPorId(id);

                            if (pessoa == null)
                            {
                                System.out.println("ID não localizado.");
                            } 
                            else 
                            {
                                pessoaJuridicaDAO.excluir(id);
                                System.out.println("Pessoa Jurídica excluída com sucesso!");
                            }
                        } 
                        else 
                        {
                            System.out.println("A opção escolhida é inválida");
                        }
                        break;
                    
                    // BUSCAR POR ID
                    case 4:
                        char busca= tipoPessoa(scanner);
                        if (busca == 'F') 
                        {
                            System.out.print("ID: ");
                            long id = scanner.nextLong();
                            scanner.nextLine(); 
                            PessoaFisica pessoa = pessoaFisicaDAO.obterPorId(id);

                            if (pessoa == null) 
                            {
                                System.out.println("ID não localizado.");
                            } 
                            else 
                            {
                                System.out.println("Dados:");
                                exibirPF(pessoa);
                            }
                        } 
                        else if (busca== 'J') 
                        {
                            System.out.print("ID: ");
                            long id = scanner.nextLong();
                            scanner.nextLine(); 

                            PessoaJuridica pessoa = pessoaJuridicaDAO.obterPorId(id);
                            if (pessoa == null) 
                            {
                                System.out.println("ID não localizado.");
                                
                            } 
                            else 
                            {
                                System.out.println("Dados:");
                                exibirPJ(pessoa);
                            }
                        } 
                        else 
                        {
                            System.out.println("Opção inválida");
                        }
                        break;
                    
                    // EXIBIR TODOS
                    case 5:

                        List<PessoaJuridica> listaPJ = pessoaJuridicaDAO.obterTodos();
                        List<PessoaFisica> listaPF = pessoaFisicaDAO.obterTodos();

                        System.out.println("Pessoas Jurídicas registradas:");
                        for (PessoaJuridica pessoa : listaPJ) {
                            exibirPJ(pessoa);
                        }

                        System.out.println("Pessoas Físicas registradas:");
                        for (PessoaFisica pessoa : listaPF) 
                        {
                            exibirPF(pessoa);
                        }
                        break;

                    // ENCERRAR
                    case 0:
                        System.out.println("Finalizado.");
                        break;

                    // OPÇÃO INCORRETA
                    default:
                        System.out.println("A opção inválida inserida não é válida. Porfavor, tente novamente.");
                        break;
                }

            } 
            while (opcao != 0);

            scanner.close();
        };
    }

    private char tipoPessoa(Scanner scanner) {
        System.out.print("F - Pessoa Fisica | J - Pessoa Juridica: ");
        char opcao = scanner.next().charAt(0);
        scanner.nextLine();
        return opcao;
    }

    private void exibirPF(PessoaFisica pessoa) {
        System.out.println("Id: " + pessoa.pegarId());
        System.out.println("Nome: " + pessoa.pegarNome());
        System.out.println("Logradouro: " + pessoa.pegarLogradouro());
        System.out.println("Cidade: " + pessoa.pegarCidade());
        System.out.println("Estado: " + pessoa.pegarEstado());
        System.out.println("Telefone: " + pessoa.pegarTelefone());
        System.out.println("Email: " + pessoa.pegarEmail());
        System.out.println("CPF: " + pessoa.pegarCpf());
    }

    private void exibirPJ(PessoaJuridica pessoa) {
        System.out.println("Id: " + pessoa.pegarId());
        System.out.println("Nome: " + pessoa.pegarNome());
        System.out.println("Logradouro: " + pessoa.pegarLogradouro());
        System.out.println("Cidade: " + pessoa.pegarCidade());
        System.out.println("Estado: " + pessoa.pegarEstado());
        System.out.println("Telefone: " + pessoa.pegarTelefone());
        System.out.println("Email: " + pessoa.pegarEmail());
        System.out.println("CNPJ: " + pessoa.pegarCnpj());
    }

    private void addDadosPF(PessoaFisica pessoa, Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        pessoa.criarNome(nome);
        System.out.print("Logradouro: ");
        pessoa.criarLogradouro(scanner.nextLine());
        System.out.print("Cidade: ");
        pessoa.criarCidade(scanner.nextLine());
        System.out.print("Estado: ");
        pessoa.criarEstado(scanner.nextLine());
        System.out.print("Telefone: ");
        pessoa.criarTelefone(scanner.nextLine());
        System.out.print("Email: ");
        pessoa.criarEmail(scanner.nextLine());
        System.out.print("CPF: ");
        pessoa.criarCpf(scanner.nextLine());
    }

    private void addDadosPJ(PessoaJuridica pessoa, Scanner scanner) {
        System.out.print("Nome: ");
        pessoa.criarNome(scanner.nextLine());
        System.out.print("Logradouro: ");
        pessoa.criarLogradouro(scanner.nextLine());
        System.out.print("Cidade: ");
        pessoa.criarCidade(scanner.nextLine());
        System.out.print("Estado: ");
        pessoa.criarEstado(scanner.nextLine());
        System.out.print("Telefone: ");
        pessoa.criarTelefone(scanner.nextLine());
        System.out.print("Email: ");
        pessoa.criarEmail(scanner.nextLine());
        System.out.print("CNPJ: ");
        pessoa.criarCnpj(scanner.nextLine());
    }
}
